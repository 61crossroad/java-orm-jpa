package org.jetlag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetlag.domain.enums.OrderStatus;

@Builder
@RequiredArgsConstructor
@Getter
public class OrderSearchDto {
    private final String memberName;
    private final OrderStatus orderStatus;
}
