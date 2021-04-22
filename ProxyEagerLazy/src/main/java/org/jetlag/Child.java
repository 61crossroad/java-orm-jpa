package org.jetlag;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
public class Child {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Parent parent;
}
