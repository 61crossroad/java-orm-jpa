package org.jetlag.domain.repository;

import org.jetlag.domain.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
