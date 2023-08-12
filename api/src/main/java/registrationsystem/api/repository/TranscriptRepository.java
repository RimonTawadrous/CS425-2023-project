package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.Transcript;

public interface TranscriptRepository extends ListCrudRepository<Transcript, Long> {
}
