package cz.polankam.jaclp.demo.model.repository;

import cz.polankam.jaclp.demo.model.dto.GroupDTO;
import cz.polankam.jaclp.demo.model.entity.GroupEntity;
import cz.polankam.jaclp.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query("SELECT DISTINCT g FROM GroupEntity g JOIN g.memberships m WHERE m.user = :user")
    List<GroupDTO> findForUser(UserEntity user);
}
