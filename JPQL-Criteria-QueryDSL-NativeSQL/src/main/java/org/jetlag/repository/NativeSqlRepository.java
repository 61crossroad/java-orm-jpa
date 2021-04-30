package org.jetlag.repository;

import org.jetlag.entity.Member;
import org.jetlag.entity.Orders;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class NativeSqlRepository {
    private final EntityManager em;

    public NativeSqlRepository(EntityManager em) { this.em = em; }

    public void resultMapping() {
        String sql = "SELECT i.id, age, name, team_id, i.order_count"
                + " FROM member m"
                + " LEFT JOIN"
                + "     (SELECT im.id, COUNT(*) AS order_count"
                + "     FROM orders o, member im"
                + "     WHERE o.member_id = im.id"
                + "     GROUP BY im.id) i"
                + " ON m.id = i.id";

        Query nativrQuery = em.createNativeQuery(sql, "memberWithOrderCount");
        List<Object[]> resultList = nativrQuery.getResultList();
        resultList.forEach(row -> {
            Member member = (Member) row[0];
            BigInteger orderCount = (BigInteger) row[1];
            System.out.println("member: " + member + ", orderCount: " + orderCount);
        });
    }

    public void selectValue() {
        String sql = "SELECT city, street, zipcode"
                + " FROM orders WHERE order_amount < ?";

        Query nativeQuery = em.createNativeQuery(sql).setParameter(1, 10);

        List<Object[]> resultList = nativeQuery.getResultList();
        resultList.forEach(o -> System.out.println(o[0] + " " + o[1] + ", " + o[2]));
    }

    public void selectEntity() {
        String sql = "SELECT id, city, street, zipcode, order_amount, member_id, product_id" +
                " FROM orders WHERE order_amount > :amount";

        /*
        JPA doesn't support named parameter... still now?
        However, Hibernate supports named parameter!

        Use Query Class for native query
         */
        Query nativeQuery = em.createNativeQuery(sql, Orders.class)
                .setParameter("amount", 10);
        List<Orders> resultList = nativeQuery.getResultList();
        resultList.forEach(System.out::println);
    }
}