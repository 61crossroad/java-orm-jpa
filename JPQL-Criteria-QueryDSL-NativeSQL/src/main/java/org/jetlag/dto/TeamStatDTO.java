package org.jetlag.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class TeamStatDTO {
    private final String name;
    private final Long count;
    private final Long sum;
    private final Double avg;
    private final int max;
    private final int min;
}
