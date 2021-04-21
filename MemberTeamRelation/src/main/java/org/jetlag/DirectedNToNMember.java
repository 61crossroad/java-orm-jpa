package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class DirectedNToNMember {
    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT_DIRECTED",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<DirectedNToNProduct> directedNToNProducts = new ArrayList<>();

    // Bidirected N:N
    public void addProduct(DirectedNToNProduct directedNToNProduct) {
        directedNToNProducts.add(directedNToNProduct);
        directedNToNProduct.getDirectedNToNMembers().add(this);
    }
}
