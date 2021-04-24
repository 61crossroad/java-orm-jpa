package org.jetlag;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class JoinMovie extends JoinItem {
    private String director;
    private String actor;
}
