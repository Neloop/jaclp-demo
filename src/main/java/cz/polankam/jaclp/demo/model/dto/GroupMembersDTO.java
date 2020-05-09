package cz.polankam.jaclp.demo.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Data
public class GroupMembersDTO {

    private List<UserDTO> managers = new ArrayList<>();
    private List<UserDTO> users = new ArrayList<>();
}
