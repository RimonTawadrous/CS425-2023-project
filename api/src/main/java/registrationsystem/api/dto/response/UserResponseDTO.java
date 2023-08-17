package registrationsystem.api.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private Boolean isActive;
}
