package cz.polankam.jaclp.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Entity
@Getter
@NoArgsConstructor
public class GroupMembershipEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @ManyToOne
    @JoinColumn
    private GroupEntity group;

    @Enumerated(value = EnumType.STRING)
    private MembershipType membershipType;

    public GroupMembershipEntity(UserEntity user, GroupEntity group, MembershipType membershipType) {
        this.user = user;
        this.group = group;
        this.membershipType = membershipType;
    }
}
