package org.jetlag;

import javax.persistence.*;
import javax.persistence.JoinTable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyToManyParent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "MANY_TO_MANY_PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private List<ManyToManyChild> manyToManyChildren = new ArrayList<>();
}
