package org.jetlag;

import javax.persistence.*;

@Entity
public class BidirectedOneToOneDominantLocker {
    @Id @GeneratedValue @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "bidirectedOneToOneDominantLocker")
    private BidirectedOneToOneDominantMember bidirectedOneToOneDominantMember;
}
