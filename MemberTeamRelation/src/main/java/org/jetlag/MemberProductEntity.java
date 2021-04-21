package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@IdClass(MemberProductEntityId.class)
@Entity
public class MemberProductEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private NToNEntityMember nToNEntityMember;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private NToNEntityProduct nToNEntityProduct;

    private int orderAmount;
}
