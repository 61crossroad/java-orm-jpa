package org.jetlag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.JoinTable;

@Entity
public class OneToManyParent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinTable(name = "ONE_TO_MANY_PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private List<OneToManyChild> oneToManyChildren = new ArrayList<>();

}
