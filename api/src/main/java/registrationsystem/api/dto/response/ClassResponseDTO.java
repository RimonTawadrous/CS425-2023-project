package registrationsystem.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassResponseDTO {

    private long id;
    private String buildingName;
    private String roomNumber;
    private FacultyProfileDTO faculty;
    private CourseResponseDTO course;
}
