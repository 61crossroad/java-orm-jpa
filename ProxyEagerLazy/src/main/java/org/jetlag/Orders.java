package org.jetlag;

import javax.persistence.*;

@Entity
public class Orders {
    @Id @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
