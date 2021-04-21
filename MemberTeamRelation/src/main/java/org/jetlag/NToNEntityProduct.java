package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class NToNEntityProduct {
    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
