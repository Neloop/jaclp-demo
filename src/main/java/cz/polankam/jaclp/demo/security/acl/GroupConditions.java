package cz.polankam.jaclp.demo.security.acl;

import cz.polankam.jaclp.demo.model.entity.GroupEntity;
import cz.polankam.jaclp.demo.model.entity.GroupMembershipEntity;
import cz.polankam.jaclp.demo.model.entity.MembershipType;
import cz.polankam.jaclp.demo.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupConditions {

    public static boolean isMember(UserDetails userDetails, GroupEntity group) {
        if (!(userDetails instanceof UserEntity) || group == null) {
            return false;
        }

        UserEntity current = (UserEntity) userDetails;
        return group.getMemberships()
                .stream()
                .map(GroupMembershipEntity::getUser)
                .anyMatch(member -> member.getId().equals(current.getId()));
    }

    public static boolean isManager(UserDetails userDetails, GroupEntity group) {
        if (!(userDetails instanceof UserEntity) || group == null) {
            return false;
        }

        UserEntity current = (UserEntity) userDetails;
        GroupMembershipEntity groupMembership = group.getMemberships()
                .stream()
                .filter(membership -> membership.getMembershipType() == MembershipType.MANAGER)
                .filter(membership -> membership.getUser().getId().equals(current.getId()))
                .findFirst()
                .orElse(null);
        return groupMembership != null;
    }
}
