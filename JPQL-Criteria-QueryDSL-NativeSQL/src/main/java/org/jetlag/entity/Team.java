package org.jetlag.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "members")
@Getter
@Entity
public class Team extends BaseEntity {
    private String name;

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
