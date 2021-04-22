package org.jetlag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyEagerLazy {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxy_eager_lazy");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {

    }

    public static void printUserAndTeam(String memberId) {
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("소속팀: " + team.getName());
    }

    public static void printUser(String memberId) {
        Member member = em.find(Member.class, memberId);
        System.out.println("회원 이름: " + member.getUsername());
    }
}
