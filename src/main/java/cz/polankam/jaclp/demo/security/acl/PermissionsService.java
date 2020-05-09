package cz.polankam.jaclp.demo.security.acl;

import cz.polankam.jaclp.demo.model.repository.GroupRepository;
import cz.polankam.security.acl.IPermissionsService;
import cz.polankam.security.acl.IResourceRepository;
import cz.polankam.security.acl.Role;
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
            GroupRepository groupRepository
    ) {
        Role user = new Role(Roles.USER);
        Role admin = new Role(Roles.ADMIN, user);

        user.addPermissionRules(
                true,
                "group",
                GroupConditions::isMember,
                "view"
        ).addPermissionRules(
                true,
                "group",
                GroupConditions::isManager,
                "update"
        );

        admin.addPermissionRules(
                true,
                "group",
                "create"
        );

        roles.put(user.getName(), user);
        roles.put(admin.getName(), admin);

        // repositories which will be used to find resources by identification
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