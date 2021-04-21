package org.jetlag;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class BidirectedNToOneTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<BidirectedNToOneMember> bidirectedNToOneMembers = new ArrayList<>();

    public void addBidirectedNToOneMember(BidirectedNToOneMember bidirectedNToOneMember) {
        this.bidirectedNToOneMembers.add(bidirectedNToOneMember);

        if (bidirectedNToOneMember.getBidirectedNToOneTeam() != this) {
            bidirectedNToOneMember.setTeam(this);
        }
    }
}
