package org.jetlag;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@AttributeOverride(name = "ID", column = @Column(name = "SELLERID"))
@Entity
public class BaseSeller extends BaseEntity {
    private String shopName;
}
