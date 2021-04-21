package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class BidirectedNToOneMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne @JoinColumn(name = "TEAM_ID")
    private BidirectedNToOneTeam bidirectedNToOneTeam;

    public void setTeam(BidirectedNToOneTeam bidirectedNToOneTeam) {
        this.bidirectedNToOneTeam = bidirectedNToOneTeam;

        if (!bidirectedNToOneTeam.getBidirectedNToOneMembers().contains(this)) {
            bidirectedNToOneTeam.getBidirectedNToOneMembers().add(this);
        }
    }
}
