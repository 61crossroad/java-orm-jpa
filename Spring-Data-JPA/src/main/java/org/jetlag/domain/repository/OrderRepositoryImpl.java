package org.jetlag.domain.repository;

import com.querydsl.jpa.JPQLQuery;
import org.jetlag.domain.entity.Orders;
import org.jetlag.domain.entity.QMember;
import org.jetlag.domain.entity.QOrders;
import org.jetlag.dto.OrderSearchDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrdersRepository {

    public OrderRepositoryImpl() {
        super(Orders.class);
    }

    @Override
    public List<Orders> search(OrderSearchDto orderSearchDto) {
        QOrders orders = QOrders.orders;
        QMember member = QMember.member;

        JPQLQuery<Orders> query = from(orders);

        if (StringUtils.hasText(orderSearchDto.getMemberName())) {
            query.leftJoin(orders.member, member)
                    .where(member.name.contains(orderSearchDto.getMemberName()));
        }

        if (orderSearchDto.getOrderStatus() != null) {
            query.where(orders.status.eq(orderSearchDto.getOrderStatus()));
        }

        return query.fetch();
    }
}
