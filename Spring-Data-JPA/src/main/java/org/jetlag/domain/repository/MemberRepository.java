package org.jetlag.domain.repository;

import org.jetlag.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom  {
    List<Member> findByEmailAndName(String email, String name);

    List<Member> findByName(@Param("name") String name);
    List<Member> findByName(String name, Sort sort);
    // List<Member> findByName(String name, Pageable pageable);
    Page<Member> findByName(String name, Pageable pageable);

    /* TODO: QueryHints!!!
    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.readOnly", value = "true")},
            forCounting = true)
    Page<Member> findByName(String name, Pageable pageable);
     */

    Page<Member> findByNameStartingWith(String name, Pageable pageable);

    @Query("select m from Member m where m.name = :name")
    Member findByNameQuery(@Param("name") String name);

    @Query(value = "SELECT * FROM member WHERE name = 0?", nativeQuery = true)
    Member findByNameNative(String name);

    // @Modifying(clearAutomatically = true)
    @Modifying
    @Query("update Product p set p.price = p.price * 1.1 where p.stockAmount < :stockAmount")
    int bulkPriceUp(@Param("stockAmount") Integer stockAmount);
}
