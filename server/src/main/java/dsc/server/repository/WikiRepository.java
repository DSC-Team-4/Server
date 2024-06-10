package dsc.server.repository;

import dsc.server.entity.Wiki;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WikiRepository extends MongoRepository<Wiki, String> {
}
