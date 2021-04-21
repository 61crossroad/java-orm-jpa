package org.jetlag;

import javax.persistence.*;

@Entity
public class DirectedNToOneMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne @JoinColumn(name = "TEAM_ID")
    private DirectedNToOneTeam directedNToOneTeam;
}
