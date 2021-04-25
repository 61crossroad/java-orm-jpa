package org.jetlag.repository;

import org.jetlag.entity.Member;
import org.jetlag.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicQueryRepository {
    private final EntityManager em;

    public DynamicQueryRepository(EntityManager em) {
        this.em = em;
    }

    public void criteriaDynamicQuery() {
        Integer age = 30;
        String username = null;
        String teamName = "team10";

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);
        Join<Member, Team> t = m.join("team");

        List<Predicate> criteria = new ArrayList<>();

        if (age != null) criteria.add(cb.lessThanOrEqualTo(
                m.<Integer>get("age"),
                cb.parameter(Integer.class, "age")));

        if (username != null) criteria.add(cb.equal(
                m.get("username"),
                cb.parameter(String.class, "username")));

        if (teamName != null) criteria.add(cb.equal(
                t.get("name"),
                cb.parameter(String.class, "teamName")));

        cq.where(cb.and(criteria.toArray(new Predicate[0])));

        TypedQuery<Member> query = em.createQuery(cq);
        if (age != null) query.setParameter("age", age);
        if (username != null) query.setParameter("username", username);
        if (teamName != null) query.setParameter("teamName", teamName);

        List<Member> resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    public void jpqlDynamicQuery() {
        Integer age = 30;
        String username = null;
        String teamName = "team10";

        StringBuilder jpql = new StringBuilder("select m from Member m join m.team t");
        List<String> criteria = new ArrayList<>();

        if (age != null) criteria.add(" m.age < :age ");
        if (username != null) criteria.add(" m.username = :username ");
        if (teamName != null) criteria.add(" t.name = :teamName ");

        if (criteria.size() > 0) jpql.append(" where ");

        for (int i = 0; i < criteria.size(); i++) {
            if (i > 0) jpql.append(" and ");
            jpql.append(criteria.get(i));
        }

        TypedQuery<Member> query = em.createQuery(jpql.toString(), Member.class);
        if (age != null) query.setParameter("age", age);
        if (username != null) query.setParameter("username", username);
        if (teamName != null) query.setParameter("teamName", teamName);

        List<Member> resultList = query.getResultList();
        resultList.forEach(result -> {
            System.out.println(result);
            System.out.println(" > " + result.getTeam());
        });
    }
}
