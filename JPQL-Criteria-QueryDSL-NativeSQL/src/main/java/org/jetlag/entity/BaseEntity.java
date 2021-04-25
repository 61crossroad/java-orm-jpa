package org.jetlag.entity;

import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

@ToString
@MappedSuperclass
public abstract class BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected <T, S, V extends Collection<T>> void setRelatedEntity(
            T _this, S _thisMany, V _thisOnesCollection, V newOnesCollection) {
        if (_thisMany != null) {
            _thisOnesCollection.remove(_this);
        }

        if (newOnesCollection != null && !newOnesCollection.contains(_this)) {
            newOnesCollection.add(_this);
        }
    }
}
