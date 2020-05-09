package cz.polankam.jaclp.demo.security;

import cz.polankam.jaclp.demo.model.entity.UserEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Service
public class SecurityContextUtils {

    /**
     * Return currently logged in user, or null.
     */
    public UserEntity getCurrentLoggedUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }

        // non authenticated anonymous user, return null right away
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return null;
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            return null;
        }

        if (principal instanceof UserEntity) {
            return (UserEntity) principal;
        } else {
            throw new RuntimeException("Bad type of UserDetails object, expected UserEntity");
        }
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
