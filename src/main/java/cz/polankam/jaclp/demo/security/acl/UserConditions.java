package cz.polankam.jaclp.demo.security.acl;

import cz.polankam.jaclp.demo.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConditions {

    public static boolean isSameUser(UserDetails userDetails, UserEntity user) {
        if (!(userDetails instanceof UserEntity) || user == null) {
            return false;
        }

        UserEntity current = (UserEntity) userDetails;
        return user.getId().equals(current.getId());
    }
}
