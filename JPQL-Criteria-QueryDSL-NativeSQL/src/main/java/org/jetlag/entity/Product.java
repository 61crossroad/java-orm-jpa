package org.jetlag.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = "orders")
@Getter
@Entity
public class Product extends BaseEntity {
    private String name;

    private int price;

    private int stockAmount;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

    public Product() {}

    public Product(String name, int price, int stockAmount) {
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
    }
}
