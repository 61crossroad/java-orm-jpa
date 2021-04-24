package org.jetlag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(IdClassParentId.class)
@Entity
public class IdClassParent {
    @Id @Column(name = "PARENT_ID!")
    private String id1;

    @Id @Column(name = "PARENT_ID2")
    private String id2;

    private String name;
}
