package org.jetlag.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
