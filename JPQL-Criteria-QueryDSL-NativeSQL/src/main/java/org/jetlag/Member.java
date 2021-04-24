package org.jetlag;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;

    public Member() {}

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
