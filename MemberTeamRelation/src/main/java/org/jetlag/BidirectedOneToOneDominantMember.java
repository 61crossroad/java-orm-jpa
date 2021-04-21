package org.jetlag;

import javax.persistence.*;

@Entity
public class BidirectedOneToOneDominantMember {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @OneToOne @JoinColumn(name = "LOCKER_ID")
    private BidirectedOneToOneDominantLocker bidirectedOneToOneDominantLocker;
}
