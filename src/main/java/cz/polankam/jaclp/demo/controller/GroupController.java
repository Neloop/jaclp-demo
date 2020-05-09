package cz.polankam.jaclp.demo.controller;

import cz.polankam.jaclp.demo.model.dto.GroupDTO;
import cz.polankam.jaclp.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupDTO> all() {
        return groupService.getAll();
    }

    @GetMapping("mine")
    public List<GroupDTO> mine() {
        return groupService.getForCurrentUser();
    }

    @GetMapping("{id}")
    public GroupDTO get(@PathVariable("id") long id) {
        return groupService.getOrThrow(id);
    }
}
