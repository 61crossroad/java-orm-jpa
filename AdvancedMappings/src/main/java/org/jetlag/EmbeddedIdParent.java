package org.jetlag;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EmbeddedIdParent {
    @EmbeddedId
    private EmbeddedIdParentId id;

    private String name;
}
