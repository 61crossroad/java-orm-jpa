package org.jetlag;

import javax.persistence.*;
import javax.persistence.JoinTable;

@Entity
public class ManyToOneChild {
    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;
    private String name;

    @ManyToOne(optional = false)
    @JoinTable(name = "MANY_TO_ONE_PARENT_CHILD",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_ID"))
    private ManyToOneParent manyToOneParent;
}
