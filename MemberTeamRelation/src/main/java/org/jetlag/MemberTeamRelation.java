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
    }
}
