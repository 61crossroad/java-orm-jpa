package org.jetlag.repository;

import org.jetlag.dto.MemberDTO;
import org.jetlag.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaRepository {
    private final EntityManager em;

     public CriteriaRepository(EntityManager em) {
         this.em = em;
     }

     public void tupleMappedEntity() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Tuple> cq = cb.createTupleQuery();
         Root<Member> m = cq.from(Member.class);

         cq.select(cb.tuple(
                 m.alias("m"),
                 m.get("username").alias("username")
         ));

         em.createQuery(cq).getResultList().forEach(tuple -> {
             Member member = tuple.get("m", Member.class);
             String username = tuple.get("username", String.class);

             System.out.println("[" + username + "] " + member);
         });
     }

     public void tuple() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Tuple> cq = cb.createTupleQuery();

         Root<Member> m = cq.from(Member.class);
         cq.multiselect(
                 m.get("username").alias("username"),
                 m.get("age").alias("age")
         );

         em.createQuery(cq).getResultList().forEach(tuple -> {
             String username = tuple.get("username", String.class);
             Integer age = tuple.get("age", Integer.class);

             System.out.println(username + ", " + age);
         });
     }

     public void criteriaConstruct() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<MemberDTO> cq = cb.createQuery(MemberDTO.class);
         Root<Member> m = cq.from(Member.class);

         cq.select(cb.construct(MemberDTO.class, m.get("username"), m.get("age")));

         TypedQuery<MemberDTO> query = em.createQuery(cq);
         List<MemberDTO> resultList = query.getResultList();
         resultList.forEach(System.out::println);
     }

     public void multiselectDistinct() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
         Root<Member> m = cq.from(Member.class);

         cq.multiselect(m.get("username"), m.get("age")).distinct(true);
         // cq.select(cb.array(m.get("username"), m.get("age"))).distinct(true);

         TypedQuery<Object[]> query = em.createQuery(cq);
         List<Object[]> resultList = query.getResultList();
         resultList.stream()
                 .map(result ->
                     new MemberDTO(result[0].toString(), (int) result[1])
                 ).forEach(System.out::println);
     }

     public void selectMemberWhereAge() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Member> cq = cb.createQuery(Member.class);
         Root<Member> m = cq.from(Member.class);

         Predicate ageGt = cb.greaterThan(m.<Integer>get("age"), 30);

         cq.select(m);
         cq.where(ageGt);
         cq.orderBy(cb.desc(m.get("age")));

         em.createQuery(cq).getResultList().forEach(System.out::println);
     }

     public void selectMemberWithWhereAndOrderBy() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Member> cq = cb.createQuery(Member.class);

         Root<Member> m = cq.from(Member.class);

         Predicate usernameEqual = cb.equal(m.get("username"), "user1");
         Order ageDesc = cb.desc(m.get("age"));

         cq.select(m).where(usernameEqual).orderBy(ageDesc);

         em.createQuery(cq).getResultList().forEach(System.out::println);
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
