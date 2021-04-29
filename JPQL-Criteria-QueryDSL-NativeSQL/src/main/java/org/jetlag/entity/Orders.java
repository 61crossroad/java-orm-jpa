package org.jetlag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

// FIXME: Mapping error, city...
@SqlResultSetMapping(name = "OrderResults",
        entities = {
                @EntityResult(
                        entityClass = org.jetlag.entity.Orders.class,
                        fields = {
                                @FieldResult(name = "id", column="order_id"),
                                @FieldResult(name = "orderAmount", column = "order_amount")
                        })},
        columns = {
                @ColumnResult(name = "product_id"),
                @ColumnResult(name = "product_name")})

@ToString(exclude = {"member", "product"})
@Setter
@Getter
@Entity
public class Orders extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        Collection<Orders> thisMemberOrders = this.member == null ? null : this.member.getOrders();
        super.setRelatedEntity(this, this.member, thisMemberOrders, member.getOrders());
        this.member = member;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setProduct(Product product) {
        Collection<Orders> thisProductOrders = this.product == null ? null : this.product.getOrders();
        super.setRelatedEntity(this, this.product, thisProductOrders, product.getOrders());
        this.product = product;
    }

    int orderAmount;

    @Embedded
    private Address address;
}
