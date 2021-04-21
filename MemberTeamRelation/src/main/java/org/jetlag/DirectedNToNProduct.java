package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Setter
@Getter
@Entity
public class DirectedNToNProduct {
    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    // Bidirected N:N
    @ManyToMany(mappedBy = "directedNToNProducts")
    private List<DirectedNToNMember> directedNToNMembers;
}
