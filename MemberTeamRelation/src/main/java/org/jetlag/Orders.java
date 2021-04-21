package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Orders {
    @Id @GeneratedValue @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne @JoinColumn(name = "MEMBER_ID")
    private NToNEntityMember nToNEntityMember;

    @ManyToOne @JoinColumn(name = "PRODUCT_ID")
    private NToNEntityProduct nToNEntityProduct;

    private int orderAmount;
}
