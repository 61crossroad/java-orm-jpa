package org.jetlag.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
