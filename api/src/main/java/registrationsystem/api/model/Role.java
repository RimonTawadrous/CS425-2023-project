package registrationsystem.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import registrationsystem.api.enums.RoleEnum;

import java.util.List;

@Entity()
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 15)
    private String internalName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
