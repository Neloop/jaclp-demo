package cz.polankam.jaclp.demo.controller;

import cz.polankam.jaclp.demo.model.dto.UserDTO;
import cz.polankam.jaclp.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasPermission('user', 'viewAll')")
    public List<UserDTO> all() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasPermission(#id, 'user', 'view')")
    public UserDTO get(@PathVariable("id") long id) {
        return userService.getOrThrow(id);
    }
}
