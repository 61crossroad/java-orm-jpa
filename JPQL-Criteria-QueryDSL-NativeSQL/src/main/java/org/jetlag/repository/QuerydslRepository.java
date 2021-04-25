package org.jetlag.repository;

import com.querydsl.core.QueryModifiers;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import org.jetlag.entity.Member;
import org.jetlag.entity.Product;
import org.jetlag.entity.QMember;
import org.jetlag.entity.QProduct;

import javax.persistence.EntityManager;
import java.util.List;

public class QuerydslRepository {
    private final EntityManager em;

    public QuerydslRepository(EntityManager em) {
        this.em = em;
    }

    public void pagingWithFetchResults() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        QueryResults<Product> results =
                query.from(product)
                        .where(product.price.gt(80000))
                        .offset(5).limit(5).fetchResults();

        Long total = results.getTotal();
        Long limit = results.getLimit();
        Long offset = results.getOffset();
        results.getResults()
                .forEach(System.out::println);
    }

    public void pagingWithQueryModifiers() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        QueryModifiers queryModifiers = new QueryModifiers(5L, 5L);
        query.from(product)
                .restrict(queryModifiers).fetch()
                .forEach(System.out::println);
    }

    public void pagingAndOrderBy() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        query.from(product)
                .where(product.price.gt(30000))
                .orderBy(product.price.desc(), product.stockAmount.asc())
                .offset(4).limit(2).fetch()
                .forEach(System.out::println);
    }

    public void selectWithWhere() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        query.from(product)
                .where(product.name.contains("product").and(product.price.gt(20000)))
                .fetch().forEach(System.out::println);
    }

    public void select() {
        QMember qMember = new QMember("m");
        JPAQuery<Member> query = new JPAQuery<>(em);

        List<Member> members = query
                .from(qMember)
                .where(qMember.username.eq("user9"))
                .orderBy(qMember.username.desc())
                .fetch();
        members.forEach(m -> System.out.println(m + " " + m.getTeam()));
    }
}
