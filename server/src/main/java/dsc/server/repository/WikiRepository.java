package dsc.server.repository;

import dsc.server.entity.Wiki;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WikiRepository extends MongoRepository<Wiki, String> {
    List<Wiki> findByTitleContainingAndCountryAndEditedAtBetween(String search, String country, LocalDateTime start, LocalDateTime end);

    List<Wiki> findByTitleContainingAndCountry(String search, String country);
}
