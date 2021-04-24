package org.jetlag;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {
    @Column(name = "city")
    private String city;

    private String street;

    @Embedded
    private Zipcode zipcode;

    protected Address() {}

    public Address(String city, String street, Zipcode zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }
}
