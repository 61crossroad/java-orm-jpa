package org.jetlag.repository;

import org.jetlag.dto.MemberDTO;
import org.jetlag.entity.Member;
import org.jetlag.entity.Orders;
import org.jetlag.entity.Product;
import org.jetlag.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaRepository {
    private final EntityManager em;

     public CriteriaRepository(EntityManager em) {
         this.em = em;
     }

     public void relatedSubQuery() {
         CriteriaBuilder cb = em.getCriteriaBuilder();

         CriteriaQuery<Member> mainQuery = cb.createQuery(Member.class);
         Root<Member> m = mainQuery.from(Member.class);

         Subquery<Team> subQuery = mainQuery.subquery(Team.class);
         Root<Member> subM = subQuery.correlate(m);

         Join<Member, Team> t = subM.join("team");

         subQuery.select(t).where(cb.equal(t.get("name"), "team10"));
         mainQuery.select(m).where(cb.exists(subQuery));

         List<Member> resultList = em.createQuery(mainQuery).getResultList();
         resultList.forEach(System.out::println);
     }

     public void simpleSubQuery() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Member> mainQuery = cb.createQuery(Member.class);

         Subquery<Double> subQuery = mainQuery.subquery(Double.class);
         Root<Member> m2 = subQuery.from(Member.class);
         subQuery.select(cb.avg(m2.<Integer>get("age")));

         Root<Member> m = mainQuery.from(Member.class);
         mainQuery.select(m)
                 .where(cb.ge(m.<Integer>get("age"), subQuery));

         TypedQuery<Member> query = em.createQuery(mainQuery);
         List<Member> resultList = query.getResultList();
         resultList.forEach(System.out::println);
     }

     public void fetchJoin() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Member> cq = cb.createQuery(Member.class);

         Root<Member> m = cq.from(Member.class);
         Fetch<Member, Team> t = m.fetch("team");

         cq.select(m).where(cb.equal(m.get("team").get("name"), "team10"));
         TypedQuery<Member> query = em.createQuery(cq);
         query.getResultList()
                 .forEach(res -> System.out.println(res + " " + res.getTeam()));
     }

     public void join() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

         Root<Member> m = cq.from(Member.class);
         Join<Member, Team> t = m.join("team", JoinType.INNER);

         cq.multiselect(m, t)
                 .where(cb.equal(t.get("name"), "team10"));
         TypedQuery<Object[]> query = em.createQuery(cq);
         query.getResultList().forEach(result -> {
             Member member = (Member) result[0];
             Team team = (Team) result[1];
             System.out.println(member + " " + team);
         });
     }

     public void sorting() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Tuple> cq = cb.createTupleQuery();
         Root<Orders> o = cq.from(Orders.class);

         Predicate orderAmountGt10 = cb.greaterThan(o.get("orderAmount"), 10);
         Order productPriceDesc = cb.desc(o.get("product").get("price"));

         cq.select(cb.tuple(
                 o.alias("o"),
                 o.get("product").alias("p")))
                 .where(orderAmountGt10)
                 .orderBy(productPriceDesc);

         em.createQuery(cq).getResultList().forEach(result -> {
             Orders order = result.get("o", Orders.class);
             Product product = result.get("p", Product.class);
             System.out.println(order + " " + product);
         });
     }

     public void groupByHaving() {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
         Root<Member> m = cq.from(Member.class);

         Expression<Integer> maxAge = cb.max(m.get("age"));
         Expression<Integer> minAge = cb.min(m.get("age"));

         cq.multiselect(m.get("team").get("name"), maxAge, minAge)
                 .groupBy(m.get("team").get("name"))
                 .having(cb.gt(minAge, 30));

         TypedQuery<Object[]> query = em.createQuery(cq);
         List<Object[]> resultList = query.getResultList();
         resultList.forEach(result -> {
             String teamName = result[0].toString();
             Integer _maxAge = (Integer) result[1];
             Integer _minAge = (Integer) result[2];
             System.out.println(teamName + " " + _minAge + " ~ " + _maxAge);
         });
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
