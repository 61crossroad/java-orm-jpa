package org.jetlag;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class Member {
    private String username;

    @ManyToOne
    private Team team;
}
