package cz.polankam.jaclp.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Data
public class GroupUpdateDTO {

    @NotBlank
    private String name;
    private String description;
}
