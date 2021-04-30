package org.jetlag.service;

import lombok.RequiredArgsConstructor;
import org.jetlag.dto.MemberListPageDto;
import org.jetlag.domain.entity.Member;
import org.jetlag.domain.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberListPageDto findByNameStartingWith(String name) {
        PageRequest pageRequest = PageRequest.of(
                0,
                10,
                Sort.by(Sort.Direction.DESC, "name"));

        Page<Member> result = memberRepository.findByNameStartingWith("kim", pageRequest);

        List<Member> members = result.getContent();
        int totalPages = result.getTotalPages();
        boolean hasNext = result.hasNext();

        return MemberListPageDto.builder()
                .members(members)
                .totalPages(totalPages)
                .hasNext(hasNext)
                .build();
    }
}
