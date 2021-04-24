package org.jetlag;

import javax.persistence.Embeddable;

@Embeddable
public class Zipcode {
    String zip;
    String plusFour;

    protected Zipcode() {}

    public Zipcode(String zip, String plusFour) {
        this.zip = zip;
        this.plusFour = plusFour;
    }
}
