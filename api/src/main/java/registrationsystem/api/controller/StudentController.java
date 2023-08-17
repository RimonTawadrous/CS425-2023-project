package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.dto.request.StudentCreationDTO;
import registrationsystem.api.dto.response.StudentProfileDTO;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.StudentProfileRepository;
import registrationsystem.api.service.StudentProfileService;
import registrationsystem.api.utils.MappingUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final ModelMapper modelMapper;
    private final MappingUtils mappingUtils;
    private final StudentProfileRepository repository;

    @Qualifier("studentProfileServiceImpl")
    private final StudentProfileService service;
    @GetMapping()
    public ResponseEntity<List<StudentProfileDTO>> index(){
        return ResponseEntity.ok(
                mappingUtils.mapList(repository.findAll(), StudentProfileDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDTO> get(@PathVariable Long id) throws RecordNotFoundException{
        StudentProfile studentProfile = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Student not found"));
        return ResponseEntity.ok(modelMapper.map(studentProfile,  StudentProfileDTO.class));
    }

    @PostMapping()
    public ResponseEntity<StudentProfileDTO> create(@RequestBody StudentCreationDTO studentCreationDTO){
        User user = modelMapper.map(studentCreationDTO, User.class);
        user.setStudentProfile(null);
        StudentProfile studentProfile = modelMapper.map(studentCreationDTO, StudentProfile.class);
        studentProfile.setUser(user);
        studentProfile = service.create(studentProfile);
        return ResponseEntity.ok(modelMapper.map(studentProfile,StudentProfileDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfileDTO> update(@PathVariable Long id, @RequestBody StudentCreationDTO studentCreationDTO )throws RecordNotFoundException{
        StudentProfile studentProfile = modelMapper.map(studentCreationDTO, StudentProfile.class);
        service.update(id, studentProfile);
        return new ResponseEntity<StudentProfileDTO>(modelMapper.map(studentProfile,StudentProfileDTO.class), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException{
        service.delete(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
