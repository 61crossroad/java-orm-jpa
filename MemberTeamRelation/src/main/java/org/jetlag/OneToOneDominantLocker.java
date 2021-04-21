package org.jetlag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OneToOneDominantLocker {
    @Id @GeneratedValue @Column(name = "LOCKER_ID")
    private Long id;

    private String name;
}
