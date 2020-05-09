package cz.polankam.jaclp.demo.model.dto;

import lombok.Data;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Data
public class GroupDTO {

    private Long id;
    private String name;
    private String description;
    private GroupMembersDTO members;
}
