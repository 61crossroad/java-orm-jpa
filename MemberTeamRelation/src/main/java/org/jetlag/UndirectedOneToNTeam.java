package org.jetlag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UndirectedOneToNTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany @JoinColumn(name = "TEAM_ID")
    private List<UndirectedOneToNMember> undirectedOneToNMembers = new ArrayList<>();
}
