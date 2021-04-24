package org.jetlag;

import javax.persistence.*;
import javax.persistence.JoinTable;

@Entity
public class OneToOneParent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;

    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private OneToOneChild oneToOneChild;
}
