package org.jetlag;

import java.io.Serializable;

public class IdClassParentId implements Serializable {
    private String id1;
    private String id2;

    public IdClassParentId() {}

    public IdClassParentId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
