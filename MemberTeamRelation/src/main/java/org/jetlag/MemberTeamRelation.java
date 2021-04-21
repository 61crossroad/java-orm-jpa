package org.jetlag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MemberTeamRelation {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("member_team");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            findInverse(em);
//            saveDirectedNToN(em);
//            findDirectedNToN(em);
//            testORM_biDirection_refactor(em);
//            testORM_biDirection(em);
//            biDirection(em);
//            deleteRelation(em);
//            updateRelation(em);
//            queryLogicJoin(em);
//            testSave(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void findInverse(EntityManager em) {
        NToNProduct product = em.find(NToNProduct.class, "productA");
        product.getNToNMembers().forEach(member -> System.out.println("member = " + member.getUsername()));
    }

    public static void saveDirectedNToN(EntityManager em) {
        NToNProduct productA = new NToNProduct();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        NToNMember member1 = new NToNMember();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.getNToNProducts().add(productA);
        em.persist(member1);
    }

    public static void findDirectedNToN(EntityManager em) {
        NToNMember member = em.find(NToNMember.class, "member1");
        member.getNToNProducts().forEach(product -> System.out.println("product.name = " + product.getName()));
    }

    public static void testORM_biDirection_refactor(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public static void testORM_biDirection(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        team1.getMembers().add(member1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        team1.getMembers().add(member2);
        em.persist(member2);
    }

    public static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, "team1");
        team.getMembers().stream().forEach(member -> System.out.println("member.username = " + member.getUsername()));
    }

    public static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    public static void updateRelation(EntityManager em) {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    public static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList()
                .forEach(member -> System.out.println("[query] member.username = " + member.getUsername()));
    }

    public static void testSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }
}
