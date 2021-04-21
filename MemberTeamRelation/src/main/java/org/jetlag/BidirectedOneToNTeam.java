package org.jetlag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BidirectedOneToNTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany @JoinColumn(name = "TEAM_ID")
    private List<BidirectedOneToNMember> bidirectedOneToNMembers = new ArrayList<>();
}
