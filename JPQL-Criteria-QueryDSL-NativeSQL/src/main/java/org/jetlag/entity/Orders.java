package org.jetlag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;

@ToString(exclude = {"member", "product"})
@Setter
@Getter
@Entity
public class Orders extends BaseEntity {
    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        Collection<Orders> thisMemberOrders = this.member == null ? null : this.member.getOrders();;
        super.setRelatedEntity(this, this.member, thisMemberOrders, member.getOrders());
        this.member = member;
    }

    @ManyToOne @JoinColumn(name = "product_id")
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
