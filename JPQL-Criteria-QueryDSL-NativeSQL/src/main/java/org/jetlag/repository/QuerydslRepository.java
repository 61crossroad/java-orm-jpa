package org.jetlag.repository;

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
