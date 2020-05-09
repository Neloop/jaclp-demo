package cz.polankam.jaclp.demo;

import cz.polankam.jaclp.demo.model.entity.UserEntity;
import cz.polankam.jaclp.demo.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Service
@RequiredArgsConstructor
public class DbInit implements InitializingBean {

    private final UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        UserEntity admin = userRepository.save(new UserEntity("admin", "admin", "password"));
        UserEntity manager = userRepository.save(new UserEntity("manager", "user", "password"));
        UserEntity user = userRepository.save(new UserEntity("user", "user", "password"));

        // TODO
    }
}
