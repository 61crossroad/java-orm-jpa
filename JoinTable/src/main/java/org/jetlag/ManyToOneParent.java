package org.jetlag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyToOneParent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manyToOneParent")
    private List<ManyToOneChild> manyToOneChildren = new ArrayList<>();
}
