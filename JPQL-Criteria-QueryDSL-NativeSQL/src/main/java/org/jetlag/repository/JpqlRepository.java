package org.jetlag.repository;

import org.jetlag.dto.TeamStatDTO;
import org.jetlag.dto.UserDTO;
import org.jetlag.entity.Member;
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
