package org.jetlag;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Setter
@Getter
@Entity
public class OneToOneDominantLocker {
    @Id @GeneratedValue @Column(name = "LOCKER_ID")
    private Long id;

    private String name;
}
