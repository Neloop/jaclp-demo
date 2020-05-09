package cz.polankam.jaclp.demo.model.mapper;

import cz.polankam.jaclp.demo.model.dto.GroupDTO;
import cz.polankam.jaclp.demo.model.dto.GroupMembersDTO;
import cz.polankam.jaclp.demo.model.entity.GroupEntity;
import cz.polankam.jaclp.demo.model.entity.GroupMembershipEntity;
import cz.polankam.jaclp.demo.model.entity.MembershipType;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    protected UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    ////////////////////////////////////////////////////////////////////////////

    @Mapping(source = "memberships", target = "members")
    public abstract GroupDTO toDTO(GroupEntity entity);

    public GroupMembersDTO toDTO(Collection<GroupMembershipEntity> memberships) {
        GroupMembersDTO members = new GroupMembersDTO();
        for (GroupMembershipEntity membership : memberships) {
            if (membership.getMembershipType() == MembershipType.MANAGER) {
                members.getManagers().add(userMapper.toDTO(membership.getUser()));
            } else if (membership.getMembershipType() == MembershipType.USER) {
                members.getUsers().add(userMapper.toDTO(membership.getUser()));
            } else {
                throw new RuntimeException("Unknown membership type '" + membership.getMembershipType() + "'");
            }
        }
        return members;
    }
}
