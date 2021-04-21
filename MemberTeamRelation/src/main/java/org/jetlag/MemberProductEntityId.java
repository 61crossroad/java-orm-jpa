package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MemberProductEntityId implements Serializable {
    private String nToNEntityMember;
    private String nToNEntityProduct;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
