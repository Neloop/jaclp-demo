package cz.polankam.jaclp.demo.service;

import cz.polankam.jaclp.demo.model.dto.UserDTO;
import cz.polankam.jaclp.demo.model.mapper.UserMapper;
import cz.polankam.jaclp.demo.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO getOrThrow(long id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID '" + id + "' not found")));
    }

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
