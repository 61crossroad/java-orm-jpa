package org.jetlag.repository;

import org.jetlag.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaRepository {
    private final EntityManager em;

     public CriteriaRepository(EntityManager em) {
         this.em = em;
     }

     public void simpleSelect() {
         // JPQL: select m from Member m

         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Member> cq = cb.createQuery(Member.class);

         Root<Member> m = cq.from(Member.class);
         cq.select(m);

         TypedQuery<Member> query = em.createQuery(cq);
         List<Member> members = query.getResultList();
         members.forEach(System.out::println);
     }
}
