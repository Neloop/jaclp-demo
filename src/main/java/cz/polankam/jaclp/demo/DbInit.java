package cz.polankam.jaclp.demo;

import cz.polankam.jaclp.demo.model.entity.GroupEntity;
import cz.polankam.jaclp.demo.model.entity.GroupMembershipEntity;
import cz.polankam.jaclp.demo.model.entity.MembershipType;
import cz.polankam.jaclp.demo.model.entity.UserEntity;
import cz.polankam.jaclp.demo.model.repository.GroupRepository;
import cz.polankam.jaclp.demo.model.repository.UserRepository;
import cz.polankam.jaclp.demo.security.acl.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Service
@RequiredArgsConstructor
public class DbInit implements InitializingBean {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        userRepository.save(new UserEntity("admin", Roles.ADMIN, passwordEncoder.encode("password")));
        UserEntity manager = userRepository.save(new UserEntity("manager", Roles.USER, passwordEncoder.encode("password")));
        UserEntity user1 = userRepository.save(new UserEntity("user-1", Roles.USER, passwordEncoder.encode("password")));
        UserEntity user2 = userRepository.save(new UserEntity("user-2", Roles.USER, passwordEncoder.encode("password")));

        GroupEntity group = groupRepository.save(new GroupEntity("demo-group", "description"));
        group.getMemberships().add(new GroupMembershipEntity(manager, group, MembershipType.MANAGER));
        group.getMemberships().add(new GroupMembershipEntity(user1, group, MembershipType.USER));
        group.getMemberships().add(new GroupMembershipEntity(user2, group, MembershipType.USER));
        // save memberships
        groupRepository.save(group);
    }
}
