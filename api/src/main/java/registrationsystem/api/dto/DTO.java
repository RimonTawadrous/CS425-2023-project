package registrationsystem.api.dto;

import org.modelmapper.ModelMapper;
import registrationsystem.api.utils.MappingUtils;

public interface DTO {
    default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils){
        return mapper;
    }
}