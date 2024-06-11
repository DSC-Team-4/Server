package dsc.server.service;

import dsc.server.dto.WikiResponse;
import dsc.server.entity.Wiki;
import dsc.server.repository.WikiRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WikiService {
    private final WikiRepository wikiRepository;

    public Long save(Wiki wiki) {
        return wikiRepository.save(wiki).getId();
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
}
