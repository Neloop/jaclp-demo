package cz.polankam.jaclp.demo.security;

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
    public PermissionsService() {
        // TODO
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