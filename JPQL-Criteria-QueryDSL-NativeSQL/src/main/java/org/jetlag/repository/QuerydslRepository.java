package org.jetlag.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryModifiers;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.jetlag.dto.ProductDTO;
import org.jetlag.dto.ProductSearchDTO;
import org.jetlag.entity.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class QuerydslRepository {
    private final EntityManager em;

    public QuerydslRepository(EntityManager em) {
        this.em = em;
    }

    public void methodDelegate() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        query.from(product).where(product.isExpensive(150000)).fetch()
                .forEach(System.out::println);
    }

    public void dynamicQuery() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        ProductSearchDTO param = new ProductSearchDTO();
        param.setName("1");
        param.setPrice(100000);

        BooleanBuilder builder = new BooleanBuilder();
        if (Optional.ofNullable(param.getName()).isPresent()) {
            builder.and(product.name.contains(param.getName()));
        }
        if (param.getPrice() != null) {
            builder.and(product.price.gt(param.getPrice()));
        }
        List<Product> result = query.from(product)
                .where(builder).fetch();
        result.forEach(System.out::println);
    }

    public void deleteBatchQuery() {
        QProduct product = QProduct.product;

        JPADeleteClause deleteClause = new JPADeleteClause(em, product);
        Long count = deleteClause.where(product.name.eq("product3")).execute();
        System.out.println("delete result: " + count);
    }

    public void updateBatchQuery() {
        QProduct product = QProduct.product;

        JPAQuery<Product> query = new JPAQuery<>(em);
        System.out.println(query.from(product).where(product.name.eq("product2")).fetchOne());

        JPAUpdateClause updateClause = new JPAUpdateClause(em, product);
        Long count = updateClause.where(product.name.eq("product2"))
                .set(product.price, product.price.add(100))
                .execute();

        // update not applied...
        System.out.println(query.from(product).where(product.name.eq("product2")).fetchOne());
    }

    public void projectionsToConstructor() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        List<ProductDTO> result = query.select(Projections.constructor(
                ProductDTO.class,
                product.name,
                product.price))
                .from(product).fetch();

        result.forEach(System.out::println);
    }
    public void projectionsToFields() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        List<ProductDTO> result = query.select(Projections.fields(
                ProductDTO.class,
                product.name.as("username"),
                product.price))
                .from(product).fetch();

        result.forEach(System.out::println);
    }
    public void projectionsToDto() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        List<ProductDTO> result = query.select(Projections.bean(
                ProductDTO.class,
                product.name.as("username"),
                product.price)).from(product).fetch();

        result.forEach(System.out::println);
    }

    public void projectTuple() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        List<Tuple> result = query.select(product.name, product.price).from(product).fetch();
        result.forEach(System.out::println);
    }

    public void projectField() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        List<String> result = query.select(product.name).from(product).fetch();
        result.forEach(System.out::println);
    }

    public void subQueryForMany() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

        query.from(product)
                .where(product.in(
                        JPAExpressions.selectFrom(productSub)
                                .where(product.name.eq(productSub.name))
                ))
                .fetch()
                .forEach(System.out::println);
    }
    public void subQueryForOne() {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

        query.from(product)
                .where(product.price.eq(
                        JPAExpressions.selectDistinct(productSub.price.max())
                                .from(productSub)
                ))
                .from(product)
                .fetch()
                .forEach(System.out::println);
    }

    public void thetaJoin() {
        JPAQuery<Orders> query = new JPAQuery<>(em);
        QOrders orders = QOrders.orders;
        QMember member = QMember.member;

        query.from(orders, member)
                .where(orders.member.eq(member))
                .fetch()
                .forEach(o -> System.out.println(o.getId() + " " + o.getMember().getUsername()));
    }
    public void fetchJoin() {
        JPAQuery<Orders> query = new JPAQuery<>(em);
        QOrders orders = QOrders.orders;
        QProduct product = QProduct.product;
        QMember member = QMember.member;

        query.from(orders)
                .innerJoin(orders.member, member).fetchJoin()
                .leftJoin(orders.product, product).fetchJoin()
                .fetch()
                .forEach(o -> System.out.println(o.getId()
                        + " " + o.getProduct().getName()
                        + " " + o.getMember().getUsername()));
    }

    public void joinOn() {
        JPAQuery<Orders> query = new JPAQuery<>(em);
        QOrders orders = QOrders.orders;
        QProduct product = QProduct.product;

        query.from(orders)
                .leftJoin(orders.product, product)
                .on(product.stockAmount.gt(10))
                .fetch()
                .forEach(System.out::println);
    }
    public void basicJoin() {
        JPAQuery<Orders> query = new JPAQuery<>(em);
        QOrders orders = QOrders.orders;
        QProduct product = QProduct.product;
        QMember member = QMember.member;

        query.from(orders)
                .join(orders.product, product)
                .leftJoin(orders.member, member)
                .fetch()
                .forEach(o -> System.out.println(o.getId() + " " + o.toString()));
        // Lazy fetch
        // .forEach(o -> System.out.println(o.getId() + " " + o.getMember() + " " + o.getProduct()));
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
