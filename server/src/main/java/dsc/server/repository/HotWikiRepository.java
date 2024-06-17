package dsc.server.repository;

import dsc.server.entity.HotWiki;
import dsc.server.entity.Wiki;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotWikiRepository extends MongoRepository<HotWiki, String> {

    List<HotWiki> findByCountry(String country);

    List<HotWiki> findByCountryAndEditedAtBetween(String country, LocalDateTime start, LocalDateTime end);

    List<HotWiki> findByEditedAtBetween(LocalDateTime start, LocalDateTime end);
}
