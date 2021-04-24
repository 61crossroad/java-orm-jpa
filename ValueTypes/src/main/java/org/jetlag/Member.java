package org.jetlag;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @Embedded Period workPeriod;
    @Embedded Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE"))
    })
    Address companyAddress;

    @Embedded PhoneNumber phoneNumber;
}
