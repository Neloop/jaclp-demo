package cz.polankam.jaclp.demo.security.acl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Roles {

    public static final String USER = "user";
    public static final String ADMIN = "admin";
}
