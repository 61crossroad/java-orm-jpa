package org.jetlag.domain.repository;

import org.jetlag.domain.entity.Orders;
import org.jetlag.dto.OrderSearchDto;

import java.util.List;

public interface CustomOrdersRepository {
    List<Orders> search(OrderSearchDto orderSearchDto);
}
