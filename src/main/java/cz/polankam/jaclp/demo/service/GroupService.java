package cz.polankam.jaclp.demo.service;

import cz.polankam.jaclp.demo.model.dto.GroupDTO;
import cz.polankam.jaclp.demo.model.entity.UserEntity;
import cz.polankam.jaclp.demo.model.mapper.GroupMapper;
import cz.polankam.jaclp.demo.model.repository.GroupRepository;
import cz.polankam.jaclp.demo.security.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GroupService {

    private final GroupRepository repository;
    private final GroupMapper mapper;
    private final SecurityContextUtils securityContextUtils;

    public GroupDTO getOrThrow(long id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group with ID '" + id + "' not found")));
    }

    public List<GroupDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<GroupDTO> getForCurrentUser() {
        UserEntity user = securityContextUtils.getCurrentLoggedUser();
        if (user == null) {
            throw new RuntimeException("Well, this was unexpected...");
        }
        return repository.findForUser(user);
    }
}
