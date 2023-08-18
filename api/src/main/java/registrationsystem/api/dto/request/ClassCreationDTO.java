package registrationsystem.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassCreationDTO {

    private String buildingName;
    private String roomNumber;
    private long CourseId;
    private long FacultyId;
}
