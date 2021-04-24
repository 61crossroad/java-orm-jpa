package org.jetlag;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class SingleMovie extends SingleItem {
}
