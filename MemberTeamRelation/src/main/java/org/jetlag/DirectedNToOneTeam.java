package org.jetlag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DirectedNToOneTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;

    private String name;
}
