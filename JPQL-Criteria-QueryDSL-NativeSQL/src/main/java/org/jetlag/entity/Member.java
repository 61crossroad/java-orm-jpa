package org.jetlag.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@SqlResultSetMapping(
        name = "memberWithOrderCount",
        entities = {@EntityResult(entityClass = Member.class)},
        columns = {@ColumnResult(name = "order_count")}
)
@NamedNativeQuery(
        name = "Member.memberWithOrderCount",
        query = "SELECT m.id, age, name, team_id, i.order_count" +
                " FROM member m" +
                "LEFT JOIN" +
                "   (SELECT im.id, COUNT(*) AS order_count" +
                "   FROM orders o, member im" +
                "   WHERE o.member_id = im.id) i" +
                " ON m.id = i.id",
        resultSetMapping = "memberWithOrderCount"
)

@NamedNativeQuery(
        name = "Member.memberSQL",
        query = "SELECT id, age, name, team_id " +
                "FROM member WHERE age > ?",
        resultClass = Member.class
)

@SqlResultSetMapping(name = "memberWithOrderCount",
        entities = {@EntityResult(entityClass = Member.class)},
        columns = {@ColumnResult(name = "order_count")}
)

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
