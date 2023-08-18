package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.dto.request.ClassCreationDTO;
import registrationsystem.api.dto.response.ClassResponseDTO;
import registrationsystem.api.model.CourseClass;
import registrationsystem.api.repository.CourseClassRepository;
import registrationsystem.api.service.ClassService;
import registrationsystem.api.service.implementation.ClassServiceImpl;
import registrationsystem.api.utils.MappingUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@AllArgsConstructor
public class ClassController {

    public final ClassServiceImpl service;
    public final CourseClassRepository repository;
    private final ModelMapper modelMapper;
    private final MappingUtils mappingUtils;

    @GetMapping
    public ResponseEntity<List<ClassResponseDTO>> index(){
        return ResponseEntity.ok(
                mappingUtils.mapList(repository.findAll(), ClassResponseDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> get(@PathVariable Long id)  throws RecordNotFoundException {
        CourseClass courseClass = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Class not found"));
        return ResponseEntity.ok(modelMapper.map(courseClass,  ClassResponseDTO.class));
    }

    @PostMapping()
    public ResponseEntity<ClassResponseDTO> create(@RequestBody ClassCreationDTO classCreationDTO) throws RecordNotFoundException {
        CourseClass courseClass = service.create(classCreationDTO);
        return ResponseEntity.ok(modelMapper.map(courseClass,ClassResponseDTO.class));
    }
}
