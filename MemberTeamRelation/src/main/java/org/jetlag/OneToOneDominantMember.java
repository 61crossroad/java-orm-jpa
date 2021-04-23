package org.jetlag;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
public class OneToOneDominantMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne @JoinColumn(name = "LOCKER_ID")
    private OneToOneDominantLocker oneToOneDominantLocker;
}
