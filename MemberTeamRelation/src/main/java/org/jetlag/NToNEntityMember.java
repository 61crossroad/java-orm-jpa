package org.jetlag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class NToNEntityMember {
    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    // For mapping entity table with composite primary key
//    @OneToMany(mappedBy = "nToNEntityMember")
//    private List<MemberProductEntity> memberProductEntities;

    @OneToMany(mappedBy = "nToNEntityMember")
    private List<Orders> orders = new ArrayList<>();
}
