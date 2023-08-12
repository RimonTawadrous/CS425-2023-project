package registrationsystem.api.utils;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import registrationsystem.api.dto.DTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MappingUtils {
    private final ModelMapper modelMapper;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
