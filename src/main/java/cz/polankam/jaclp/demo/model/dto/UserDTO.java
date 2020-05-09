package cz.polankam.jaclp.demo.model.dto;

import lombok.Data;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Data
public class UserDTO {

    private Long id;
    private String username;
    private boolean enabled;
    private String role;
}
