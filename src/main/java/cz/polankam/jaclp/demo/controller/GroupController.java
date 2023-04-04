package cz.polankam.jaclp.demo.controller;

import cz.polankam.jaclp.demo.model.dto.GroupDTO;
import cz.polankam.jaclp.demo.model.dto.GroupUpdateDTO;
import cz.polankam.jaclp.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
    @PreAuthorize("hasPermission('group', 'viewAll')")
    public List<GroupDTO> all() {
        return groupService.getAll();
    }

    @GetMapping("mine")
    @PreAuthorize("hasPermission('group', 'viewMine')")
    public List<GroupDTO> mine() {
        return groupService.getForCurrentUser();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasPermission(#id, 'group', 'view')")
    public GroupDTO get(@PathVariable("id") long id) {
        return groupService.getOrThrow(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasPermission(#id, 'group', 'update')")
    public GroupDTO update(@PathVariable("id") long id, @Valid @RequestBody GroupUpdateDTO updateDTO) {
        return groupService.update(id, updateDTO);
    }
}
