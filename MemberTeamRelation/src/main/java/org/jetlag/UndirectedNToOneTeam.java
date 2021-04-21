package org.jetlag;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class UndirectedNToOneTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<UndirectedNToOneMember> undirectedNToOneMembers = new ArrayList<>();

    public void addUndirectedNToOneMember(UndirectedNToOneMember undirectedNToOneMember) {
        this.undirectedNToOneMembers.add(undirectedNToOneMember);

        if (undirectedNToOneMember.getUndirectedNToOneTeam() != this) {
            undirectedNToOneMember.setTeam(this);
        }
    }
}
