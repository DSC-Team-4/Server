package dsc.server.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import dsc.server.dto.WikiEwmaRequest;
import dsc.server.dto.WikiEwmaRequest.WikiInfo;
import dsc.server.dto.WikiUpdateRequest;
import dsc.server.dto.WikiResponse;
import dsc.server.entity.NotEwmaWiki;
import dsc.server.entity.Wiki;
import dsc.server.repository.NotEwmaWikiRepository;
import dsc.server.repository.WikiRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WikiService {
    private final NotEwmaWikiRepository notEwmaWikiRepository;
    private final WikiRepository wikiRepository;
    private final MongoTemplate mongoTemplate;

    public Long save(NotEwmaWiki notEwmaWiki) {
        return notEwmaWikiRepository.save(notEwmaWiki).getId();
    }

    public List<Wiki> getAll() {
        return wikiRepository.findAll();
    }

    public List<WikiResponse> findByFilter(String search, String period, String country) {
        if (!"all".equals(period)) {
            LocalDateTime start = LocalDateTime.now().minusMonths(Integer.parseInt(period));
            LocalDateTime end = LocalDateTime.now();

            List<Wiki> findWikis = wikiRepository.findByTitleContainingAndCountryAndEditedAtBetween(search, country, start, end);
            return WikiResponse.ofList(findWikis);
        }

        List<Wiki> findWikis = wikiRepository.findByTitleContainingAndCountry(search, country);
        return WikiResponse.ofList(findWikis);
    }

    public List<WikiEwmaRequest> getWikisForUpdateEwma() {
        List<NotEwmaWiki> findNotEwmaWikis = notEwmaWikiRepository.findByEwmaIsNull();
        Map<UUID, List<NotEwmaWiki>> wikisByMetaId = findNotEwmaWikis.stream().collect(Collectors.groupingBy(
                dsc.server.entity.NotEwmaWiki::getMetaId));

        List<WikiEwmaRequest> result = new ArrayList<>();

        for (Entry<UUID, List<NotEwmaWiki>> entry : wikisByMetaId.entrySet()) {
            List<WikiInfo> wikiInfos = new ArrayList<>();
            Wiki beforeWiki = null;

            for (int i = 0; i < entry.getValue().size(); i++) {
                NotEwmaWiki notEwmaWiki = entry.getValue().get(i);

                Optional<Wiki> beforeWikiOptional = wikiRepository.findTop1ByMetaIdAndEwmaIsNotNullOrderByEditedAtDesc(
                        entry.getKey());

                if (beforeWikiOptional.isPresent()) {
                    beforeWiki = beforeWikiOptional.get();

                    WikiInfo newWikiInfo = new WikiInfo(
                            notEwmaWiki.getTitle(),
                            notEwmaWiki.getCountry(),
                            notEwmaWiki.getUri(),
                            beforeWiki.getEditCount() + i,
                            notEwmaWiki.getEditedAt()
                    );

                    wikiInfos.add(newWikiInfo);
                } else {
                    Wiki newWiki = new Wiki(
                            notEwmaWiki.getTitle(),
                            notEwmaWiki.getCountry(),
                            notEwmaWiki.getUri(),
                            notEwmaWiki.getMetaId(),
                            1.0,
                            1,
                            notEwmaWiki.getEditedAt());

                    beforeWiki = newWiki;
                    wikiRepository.save(newWiki);
                }
            }

            if (!wikiInfos.isEmpty()) {
                WikiEwmaRequest request = new WikiEwmaRequest(entry.getKey(),
                        beforeWiki.getEwma(), wikiInfos);

                result.add(request);
            }
        }

        notEwmaWikiRepository.deleteAll();
        return result;
    }

    public void updateEwma(List<WikiUpdateRequest> updateRequest) {
        List<Wiki> saveWikis = new ArrayList<>();

        for (WikiUpdateRequest request : updateRequest) {
            Wiki findWiki = wikiRepository.findByMetaId(request.metaId()).orElseThrow();
            Double newEwma = request.ewma();
            Long id = findWiki.getId();

            updateWikiEwma(newEwma, id);
        }
    }

    public List<Wiki> findByEditedAtBetween(LocalDateTime threeHoursAgo, LocalDateTime now) {
        return wikiRepository.findByEditedAtBetween(threeHoursAgo, now);
    }

    private void updateWikiEwma(Double newEwma, Long id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = Update.update("ewma", newEwma);
        mongoTemplate.updateFirst(query, update, Wiki.class);
    }
}
