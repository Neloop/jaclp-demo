package cz.polankam.jaclp.demo.model.repository;

import cz.polankam.jaclp.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Repository
public interface UserRepository extends JpaRepository<Long, UserEntity> {

    Optional<UserEntity> findByUsername(String username);
}
