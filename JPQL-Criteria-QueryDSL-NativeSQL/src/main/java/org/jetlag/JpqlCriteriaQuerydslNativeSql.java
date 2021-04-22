package org.jetlag;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpqlCriteriaQuerydslNativeSql {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql_criteria_querydsl_nativesql");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {

    }

    public static void createQueryQuerydsl() {
        JPAQuery<Member> query = new JPAQuery(em);
        QMember member = QMember.member;

        List<Member> members =
                query.select(member).where(member.username.eq("kim")).fetch();
    }

    public static void createQueryCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq =
                query.select(m)
                        .where(cb.equal(m.get("username"), "kim"));

        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    public static void createQueryJqpl() {
        String jpql = "select m from Member as m where m.username = 'kim'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
    }
}
