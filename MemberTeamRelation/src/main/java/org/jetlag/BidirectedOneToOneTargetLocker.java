package org.jetlag;

import javax.persistence.*;

@Entity
public class BidirectedOneToOneTargetLocker {
    @Id @GeneratedValue @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne @JoinColumn(name = "MEMBER_ID")
    private BidirectedOneToOneTargetMember bidirectedOneToOneTargetMember;
}
