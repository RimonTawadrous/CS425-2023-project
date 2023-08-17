package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.dto.request.FacultyCreationDTO;
import registrationsystem.api.dto.request.StudentCreationDTO;
import registrationsystem.api.dto.response.FacultyProfileDTO;
import registrationsystem.api.dto.response.StudentProfileDTO;
import registrationsystem.api.model.FacultyProfile;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.FacultyProfileRepository;
import registrationsystem.api.repository.StudentProfileRepository;
import registrationsystem.api.service.FacultyProfileService;
import registrationsystem.api.service.StudentProfileService;
import registrationsystem.api.utils.MappingUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculties")
@AllArgsConstructor
public class FacultyController {
    private final ModelMapper modelMapper;
    private final MappingUtils mappingUtils;
    private final FacultyProfileRepository repository;

    @Qualifier("facultyProfileServiceImpl")
    private final FacultyProfileService service;
    @GetMapping()
    public ResponseEntity<List<FacultyProfileDTO>> index(){
        return ResponseEntity.ok(
                mappingUtils.mapList(repository.findAll(), FacultyProfileDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyProfileDTO> get(@PathVariable Long id) throws RecordNotFoundException{
        FacultyProfile facultyProfile = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Faculty not found"));
        return ResponseEntity.ok(modelMapper.map(facultyProfile,  FacultyProfileDTO.class));
    }

    @PostMapping()
    public ResponseEntity<FacultyProfileDTO> create(@RequestBody FacultyCreationDTO facultyCreationDTO){
        User user = modelMapper.map(facultyCreationDTO, User.class);
        user.setStudentProfile(null);
        FacultyProfile facultyProfile = modelMapper.map(facultyCreationDTO, FacultyProfile.class);
        facultyProfile.setUser(user);
        facultyProfile = service.create(facultyProfile);
        return ResponseEntity.ok(modelMapper.map(facultyProfile,FacultyProfileDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyProfileDTO> update(@PathVariable Long id, @RequestBody FacultyCreationDTO facultyCreationDTO )throws RecordNotFoundException{
        FacultyProfile facultyProfile = modelMapper.map(facultyCreationDTO, FacultyProfile.class);
        service.update(id, facultyProfile);
        return new ResponseEntity<FacultyProfileDTO>(modelMapper.map(facultyProfile,FacultyProfileDTO.class), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException{
        service.delete(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
