package org.jetlag.service;

import lombok.RequiredArgsConstructor;
import org.jetlag.domain.entity.Orders;
import org.jetlag.domain.repository.OrdersRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import org.jetlag.domain.spec.OrderSpec;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public List<Orders> findOrders(String name, String status) {
        List<Orders> result = ordersRepository.findAll(
                Specification.where(OrderSpec.memberName(name)).and(OrderSpec.isOrderStatus(status)));

        return result;
    }
}
