package org.jetlag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        try {
            tx.begin();
            init(10);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            em.close();
        }
        emf.close();

//        createQueryQuerydsl();
    }
/*
    public static void createQueryNativeSql() {
        String sql = "SELECT id, age, team_id, name FROM member WHERE name = 'kim'";
        List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
    }

    public static void createQueryQuerydsl() {
        JPAQuery<Member> query = new JPAQuery(em);
        QMember member = QMember.member;

        List<Member> members =
                query.select(member).where(member.username.eq("kim")).fetch();
        members.forEach(System.out::println);
    }
*/
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

    private static void init(int n) {
        Member memberKim = new Member("kim", 17);
        em.persist(memberKim);

        for (int i = 1; i <= n; i++) {
            Member member = new Member("회원" + i, 20 + i);
            em.persist(member);
        }
    }
}
