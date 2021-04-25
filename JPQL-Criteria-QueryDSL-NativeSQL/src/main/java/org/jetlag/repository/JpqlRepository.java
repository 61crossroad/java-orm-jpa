package org.jetlag.repository;

import org.jetlag.dto.TeamStatDTO;
import org.jetlag.dto.UserDTO;
import org.jetlag.entity.Member;
import org.jetlag.entity.Orders;
import org.jetlag.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpqlRepository {
    private final EntityManager em;

    public JpqlRepository(EntityManager em) {
        this.em = em;
    }

    public void namedQueriesFromXml() {
        em.createNamedQuery("Member.findByUsernameXml", Member.class)
                .setParameter("username", "user4")
                .getResultList()
                .forEach(System.out::println);

        Long count = em.createNamedQuery("Member.countXml", Long.class).getSingleResult();
        System.out.println("count: " + count);
    }
    public void namedQueries() {
        em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", "user7")
                .getResultList()
                .forEach(System.out::println);
    }

    public void collectionEquation() {
        TypedQuery<Team> query = em.createQuery("select t from Team t" +
                " where :memberParam member of t.members", Team.class);
        Member member = em.createQuery("select m from Member m where m.username = :username",
                Member.class).setParameter("username", "user4").getSingleResult();
        query.setParameter("memberParam", member);

        query.getResultList().forEach(System.out::println);
    }

    public void subQueryFunctions() {
        em.createQuery("select m from Member m" +
                " where exists (select t from m.team t where t.name = 'team10')", Member.class)
                .getResultList().forEach(member -> System.out.println(member + " " + member.getTeam()));

        em.createQuery("select o from Orders o" +
                " where o.orderAmount > any(select p.stockAmount from Product p)", Orders.class)
                .getResultList().forEach(System.out::println);

        em.createQuery("select m from Member m" +
                " where m.team = any(select t from Team t)", Member.class)
                .getResultList().forEach(System.out::println);

        em.createQuery("select t from Team t" +
                " where t in(select t2 from Team t2 join t2.members m2 where m2.age >= 30)", Team.class)
                .getResultList().forEach(System.out::println);
    }

    public void subQuries() {
        TypedQuery<Member> query = em.createQuery(
                "select m from Member m" +
                        " where m.age > (select avg(m2.age) from Member m2)",
                Member.class);
        query.getResultList().forEach(member -> System.out.println(member + " " + member.getOrders()));

        em.createQuery("select m from Member m" +
                " where (select count(o) from Orders o where m = o.member) > 0", Member.class)
                .getResultList().forEach(System.out::println);

        em.createQuery("select m from Member m where m.orders.size > 0", Member.class)
                .getResultList().forEach(System.out::println);
    }

    public void collectionPathExpression() {
        TypedQuery<String> query = em.createQuery(
                "select m.username from Team t join t.members m",
                String.class);
        query.getResultList().forEach(System.out::println);

        em.createQuery("select t.members.size from Team t", Integer.class)
                .getResultList().forEach(System.out::println);
    }
    public void pathExpression() {
        String jpql = "select o.member.team from Orders o" +
                " where o.product.name = 'product20' and o.address.city = 'city10'";
        TypedQuery<Team> query = em.createQuery(jpql, Team.class);
        query.getResultList().forEach(System.out::println);

//        em.createQuery(jpql, Team.class).getResultList()
//                .forEach(System.out::println);
    }

    public void collectionFetchJoin() {
        String jpql = "select distinct t from Team t join fetch t.members";
        em.createQuery(jpql, Team.class).getResultList()
                .forEach(team -> {
                    System.out.println(team);
                    team.getMembers().forEach(System.out::println);
                });
    }

    public void fetchJoin() {
        String jpql = "select m from Member m join fetch m.team";

        List<Member> members = em.createQuery(jpql, Member.class).getResultList();
        members.forEach(member -> System.out.println(member + " " + member.getTeam()));
    }

    public void collectionJoin() {
        String query = "select t from Team t left join t.members m";
        em.createQuery(query, Team.class).getResultList()
                .forEach(System.out::println);
//                .forEach(team -> {
//                    System.out.println(team);
//                    team.getMembers().forEach(System.out::println);
//                });
    }

    public void innerJoin() {
        String teamName = "team10";

//        String query = "select m from Member m inner join m.team t where t.name = :teamName";
//        List<Member> members = em.createQuery(query, Member.class)
//                .setParameter("teamName", teamName)
//                .getResultList();

        String query = "select m from Member m inner join m.team t";
        List<Member> members = em.createQuery(query, Member.class).getResultList();
//        members.forEach(member -> System.out.println(member.toString() + " " + member.getTeam()));
        members.forEach(System.out::println);
    }

    public void groupByHaving() {
        TypedQuery<TeamStatDTO> query =
                em.createQuery("select new org.jetlag.dto.TeamStatDTO(" +
                        " t.name, count (m.age), sum(m.age), avg(m.age), max(m.age), min(m.age))" +
                        " from Member m left join m.team t" +
                        " group by t.name having 20 <= avg(m.age) and avg(m.age) <= 30", TeamStatDTO.class);

        query.getResultList().forEach(System.out::println);
    }

    public void paging() {
        TypedQuery<Member> query =
                em.createQuery("select m from Member m order by m.username desc",
                        Member.class);

        query.setFirstResult(3);
        query.setMaxResults(5);
        query.getResultList().forEach(System.out::println);
    }

    public void mappingDtoInTypedQuery() {
        TypedQuery<UserDTO> query =
                em.createQuery("select new org.jetlag.dto.UserDTO(m.username, m.age)" +
                        " from Member m", UserDTO.class);

        query.getResultList().forEach(System.out::println);
    }

    public void positionalParameter() {
        em.createQuery("select m from Member m where m.username = ?1", Member.class)
                .setParameter(1, "회원2")
                .getResultList()
                .forEach(System.out::println);
    }

    public void namedParameter() {
        String usernameParam = "회원1";

        TypedQuery<Member> query = em.createQuery("select m from Member m" +
                " where m.username = :username", Member.class);
        query.setParameter("username", usernameParam);
        query.getResultList().forEach(System.out::println);
    }

    public void query() {
        Query query = em.createQuery("select m.username, m.age from Member m");
        query.getResultList().forEach(result -> {
            Object[] resultArray = (Object[]) result;
            System.out.println("username = " + resultArray[0]
                    + ", age = " + resultArray[1]);
        });
    }

    public void typedQuery() {
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        query.getResultList().forEach(System.out::println);
    }
}
