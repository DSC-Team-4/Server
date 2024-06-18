package dsc.server.repository;

import dsc.server.entity.Wiki;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WikiRepository extends MongoRepository<Wiki, String> {
    List<Wiki> findByCountryAndEditedAtBetweenOrderByEwmaDesc(String country, LocalDateTime start, LocalDateTime end);
    List<Wiki> findByCountryOrderByEwmaDesc(String country);
    Optional<Wiki> findTop1ByMetaIdAndEwmaIsNotNullOrderByEditedAtDesc(Long metaId);
    Optional<Wiki> findByMetaId(Long metaId);
    List<Wiki> findByEditedAtBetweenOrderByEwmaDesc(LocalDateTime start, LocalDateTime end);
    List<Wiki> findByEditedAtBetween(LocalDateTime threeHoursAgo, LocalDateTime now);
    List<Wiki> findAllByOrderByEwmaDesc();
}
