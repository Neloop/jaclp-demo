package cz.polankam.jaclp.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Entity
@Getter
@NoArgsConstructor
public class GroupEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotBlank
    private String name;

    @Lob
    @Setter
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "group")
    private List<GroupMembershipEntity> memberships = new ArrayList<>();

    public GroupEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
