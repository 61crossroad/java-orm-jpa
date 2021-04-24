package org.jetlag;

import javax.persistence.*;

@Entity
public class OneToOneChild {
    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;
    private String name;

    // for bidirectional mapping
    @OneToOne(mappedBy = "child")
    private OneToOneParent oneToOneParent;
}
