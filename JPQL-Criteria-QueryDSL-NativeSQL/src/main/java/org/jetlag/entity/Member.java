package org.jetlag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = "team")
@Setter
@Getter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member() {}

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;

        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
