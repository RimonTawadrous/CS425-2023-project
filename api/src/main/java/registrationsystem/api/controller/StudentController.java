package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
//        User user = modelMapper.map(studentCreationDTO, User.class);
//        System.out.println(user.getFirstName());
        User user = User.builder()
                .email(studentCreationDTO.getEmail())
                .username(studentCreationDTO.getUsername())
                .password(studentCreationDTO.getPassword())
                .firstName(studentCreationDTO.getFirstName())
                .lastName(studentCreationDTO.getLastName())
                .build();

        System.out.println(user);
        StudentProfile studentProfile = modelMapper.map(studentCreationDTO, StudentProfile.class);
        studentProfile.setUser(user);
        studentProfile = service.create(studentProfile);
        return ResponseEntity.ok(modelMapper.map(studentProfile,StudentProfileDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> update(){

        return ResponseEntity.ok(new StudentProfile());
    }
}
