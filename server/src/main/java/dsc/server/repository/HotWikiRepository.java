package dsc.server.repository;

import dsc.server.entity.HotWiki;
import dsc.server.entity.Wiki;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotWikiRepository extends MongoRepository<HotWiki, String> {

}
