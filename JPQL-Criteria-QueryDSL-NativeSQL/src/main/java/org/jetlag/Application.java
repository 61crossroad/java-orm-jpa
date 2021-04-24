package org.jetlag;

import com.querydsl.jpa.impl.JPAQuery;
import org.jetlag.entity.Member;
import org.jetlag.entity.QMember;
import org.jetlag.entity.Team;
import org.jetlag.repository.JpqlRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        JpqlRepository jpqlRepository = new JpqlRepository(em);

        try {
            tx.begin();
            init(10);
//            jpqlRepository.positionalParameter();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            em.close();
        }
        emf.close();
    }

    /*
    private static void createQueryNativeSql() {
        String sql = "SELECT id, age, team_id, name FROM member WHERE name = 'kim'";
        List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
    }
     */

    private static void createQueryQuerydsl() {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember member = QMember.member;

        List<Member> members =
                query.select(member).from(member).where(member.username.eq("kim")).fetch();
        members.forEach(System.out::println);
    }

    private static void createQueryCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq =
                query.select(m)
                        .where(cb.equal(m.get("username"), "kim"));

        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    private static void createQueryJqpl() {
        String jpql = "select m from Member as m where m.username = 'kim'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
    }

    private static void init(int n) {
        Member memberKim = new Member("kim", 17);
        em.persist(memberKim);

        List<Team> teams = new ArrayList<>();
        for (int i = 0; i <= n / 2; i++) {
            Team team = new Team("team" + (i + 1));
            teams.add(team);
            em.persist(team);
        }

        for (int i = 0; i < n; i++) {
            Member member = new Member("user" + (i + 1), 20 + i);
            Team team = teams.get(i / 2);
            member.setTeam(team);
            em.persist(member);
        }
    }
}
