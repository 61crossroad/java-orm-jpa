package org.jetlag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlCriteriaQuerydslNativeSql {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql_criteria_querydsl_nativesql");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {

    }

    public static void createQueryJqpl() {
        String jpql = "select m from Member as m where m.username = 'kim'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
    }
}
