package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class NToNMember {
    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<NToNProduct> nToNProducts = new ArrayList<>();

    // Bidirected N:N
    public void addProduct(NToNProduct nToNProduct) {
        this.nToNProducts.add(nToNProduct);
        nToNProduct.getNToNMembers().add(this);
    }
}
