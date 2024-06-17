package dsc.server.service;

import dsc.server.dto.HotWikiResponse;
import dsc.server.entity.HotWiki;
import dsc.server.repository.HotWikiRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotWikiService {
    private final HotWikiRepository hotWikiRepository;

    public List<HotWikiResponse> getAllHotWikis() {
        List<HotWiki> hotWikis = hotWikiRepository.findAll();

        return getHotWikiResponses(hotWikis);
    }

    public List<HotWikiResponse> findByFilter(String period, String country) {
        if (!"all".equals(period)) {
            LocalDateTime start = LocalDateTime.now().minusMonths(Integer.parseInt(period));
            LocalDateTime end = LocalDateTime.now();

            if (!"all".equals(country)) {
                List<HotWiki> findWikis = hotWikiRepository.findByCountryAndEditedAtBetween(country, start, end);

                return getHotWikiResponses(findWikis);
            }

            List<HotWiki> findWikis = hotWikiRepository.findByEditedAtBetween(start, end);

            return getHotWikiResponses(findWikis);
        }else { // 전체 기간 선택
            if (!"all".equals(country)) {
                List<HotWiki> findWikis = hotWikiRepository.findByCountry(country);
                
                return getHotWikiResponses(findWikis);
            }
        }

        return getAllHotWikis();
    }

    private List<HotWikiResponse> getHotWikiResponses(List<HotWiki> findWikis) {
        Map<LocalDateTime, List<HotWiki>> hotWikis = findWikis.stream()
                .collect(Collectors.groupingBy(HotWiki::getCreatedAt));

        return HotWikiResponse.ofList(hotWikis);
    }
}
