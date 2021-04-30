package org.jetlag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetlag.entity.Member;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberListPageDto {
    private final List<Member> members;
    private final int totalPages;
    private final boolean hasNext;
}
