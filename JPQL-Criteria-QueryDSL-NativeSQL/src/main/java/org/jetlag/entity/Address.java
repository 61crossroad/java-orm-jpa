package org.jetlag.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
