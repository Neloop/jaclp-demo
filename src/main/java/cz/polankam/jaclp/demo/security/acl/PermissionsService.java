package cz.polankam.jaclp.demo.security.acl;

import cz.polankam.jaclp.demo.model.repository.GroupRepository;
import cz.polankam.jaclp.demo.model.repository.UserRepository;
import cz.polankam.security.acl.IPermissionsService;
import cz.polankam.security.acl.IResourceRepository;
import cz.polankam.security.acl.Role;
import cz.polankam.security.acl.RoleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PermissionsService implements IPermissionsService {

    private Map<String, Role> roles = new HashMap<>();
    private Map<String, IResourceRepository> resources = new HashMap<>();

    /**
     * Default constructor which initialize all user roles used within
     * application and assign permission rules to them.
     */
    @Autowired
    public PermissionsService(
            UserRepository userRepository,
            GroupRepository groupRepository
    ) {
        Role user = RoleBuilder.create(Roles.USER)
                .addAllowedRule(
                        "user",
                        UserConditions::isSameUser,
                        "view")
                .addAllowedRule("group", "viewMine")
                .addAllowedRule(
                        "group",
                        GroupConditions::isMember,
                        "view")
                .addAllowedRule(
                        "group",
                        GroupConditions::isManager,
                        "update")
                .build();

        // admin can do everything in the system
        Role admin = RoleBuilder.create(Roles.ADMIN)
                .parent(user)
                .addAllowedRule("*", "*")
                .build();

        roles.put(user.getName(), user);
        roles.put(admin.getName(), admin);

        // repositories which will be used to find resources by identification
        resources.put("user", userRepository);
        resources.put("group", groupRepository);
    }

    public boolean roleExists(String role) {
        return roles.containsKey(role);
    }

    public Role getRole(String roleString) {
        Role role = roles.get(roleString);
        if (role == null) {
            throw new RuntimeException("Role '" + roleString + "' not found");
        }

        return role;
    }

    public IResourceRepository getResource(String resource) {
        IResourceRepository repository = resources.get(resource);
        if (repository == null) {
            throw new RuntimeException("Resource '" + resource + "' not found");
        }

        return repository;
    }
}