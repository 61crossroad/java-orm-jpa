package org.jetlag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NamedQuery(
        name = "Member.findByName",
        query = "select m from Member m where m.name = :name"
)
@ToString
@Setter
@Getter
@Entity
public class Member extends {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private int age;

    /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void setTeam(Team team) {
        Collection<Member> thisTeamMembers = this.team == null ? null : this.team.getMembers();
        super.setRelatedEntity(this, this.team, thisTeamMembers, team.getMembers());
        this.team = team;
    }

    @OneToMany(mappedBy = "member")
    private List<Orders> orders; */

    public Member() {}

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
