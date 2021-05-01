package org.jetlag.domain.repository;

import org.jetlag.domain.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    @Override
    public List<Member> findMemberCustom() {
        return null;
    }
}
