package org.jetlag;

import javax.persistence.*;

@Entity
public class BidirectedOneToOneTargetMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @OneToOne(mappedBy = "bidirectedOneToOneTargetMember")
    private BidirectedOneToOneTargetLocker bidirectedOneToOneTargetLocker;
}
