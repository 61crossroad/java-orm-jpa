package org.jetlag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Member.findByUsername",
                query = "select m from Member m where m.username = :username"),
        @NamedQuery(
                name = "Member.count",
                query = "select count(m) from Member m")
})
@ToString(exclude = {"team", "orders"})
@Setter
@Getter
@Entity
public class Member extends BaseEntity {
    @Column(name = "name")
    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void setTeam(Team team) {
        Collection<Member> thisTeamMembers = this.team == null ? null : this.team.getMembers();
        super.setRelatedEntity(this, this.team, thisTeamMembers, team.getMembers());
        this.team = team;
    }

    @OneToMany(mappedBy = "member")
    private List<Orders> orders;

    public Member() {}

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
