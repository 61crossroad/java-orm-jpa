package org.jetlag;

import javax.persistence.*;

@Entity
public class UndirectedOneToNMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private UndirectedOneToNTeam undirectedOneToNTeam;
}
