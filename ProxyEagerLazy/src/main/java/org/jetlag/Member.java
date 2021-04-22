package org.jetlag;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Member {
    @Id
    private String id;
    private String username;
    private Integer age;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID", nullable = false /* INNER JOIN */)
    private Team team;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Orders> orders;
}
