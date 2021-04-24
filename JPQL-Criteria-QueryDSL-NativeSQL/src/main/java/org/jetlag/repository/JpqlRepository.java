package org.jetlag.repository;

import org.jetlag.dto.UserDTO;
import org.jetlag.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpqlRepository {
    private final EntityManager em;

    public JpqlRepository(EntityManager em) {
        this.em = em;
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
