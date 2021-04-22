package org.jetlag;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Parent {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "parent",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<Child> children = new ArrayList<>();
}
