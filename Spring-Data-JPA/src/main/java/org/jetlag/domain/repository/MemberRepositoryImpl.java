package org.jetlag.domain.repository;

import lombok.RequiredArgsConstructor;
import org.jetlag.domain.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl {
    private final EntityManager em;

    List<Member> findByName(String name) {
        return em.createNamedQuery("Member.findByName", Member.class)
                .setParameter("name", "user1")
                .getResultList();
    }
}
