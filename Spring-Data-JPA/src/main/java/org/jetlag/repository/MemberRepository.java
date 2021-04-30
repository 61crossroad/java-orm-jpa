package org.jetlag.repository;

import org.jetlag.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmailAndName(String email, String name);

    List<Member> findByName(@Param("name") String name);

    @Query("select m from Member m where m.name = :name")
    Member findByNameQuery(@Param("name") String name);

    @Query(value = "SELECT * FROM member WHERE name = 0?", nativeQuery = true)
    Member findByNameNative(String name);
}
