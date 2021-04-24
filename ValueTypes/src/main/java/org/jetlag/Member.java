package org.jetlag;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @Embedded Period workPeriod;
    @Embedded Address homeAddress;
    @Embedded PhoneNumber phoneNumber;
}
