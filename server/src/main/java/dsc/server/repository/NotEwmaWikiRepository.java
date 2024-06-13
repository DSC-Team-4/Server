package dsc.server.repository;

import dsc.server.entity.NotEwmaWiki;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotEwmaWikiRepository extends MongoRepository<NotEwmaWiki, String> {
    List<NotEwmaWiki> findByTitleContainingAndCountryAndEditedAtBetween(String search, String country, LocalDateTime start, LocalDateTime end);
    List<NotEwmaWiki> findByTitleContainingAndCountry(String search, String country);
    List<NotEwmaWiki> findByEwmaIsNull();
    Optional<NotEwmaWiki> findTop1ByMetaIdAndEwmaIsNotNullOrderByEditedAtDesc(UUID metaId);
}
