package registrationsystem.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import registrationsystem.api.model.User;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileDTO {
    private long id;
    private long studentNumber;
    private float cgpa;
    private LocalDate dateOfEnrollment;
    private UserResponseDTO user;
}
