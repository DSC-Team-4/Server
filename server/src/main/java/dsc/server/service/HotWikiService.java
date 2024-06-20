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

    public List<HotWiki> findByTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return hotWikiRepository.findByEditedAtBetweenOrderByEwmaDesc(startTime, endTime);
    }

    private List<HotWikiResponse> getHotWikiResponses(List<HotWiki> findWikis) {
        Map<LocalDateTime, List<HotWiki>> hotWikis = findWikis.stream()
                .collect(Collectors.groupingBy(HotWiki::getCreatedAt));

        return HotWikiResponse.ofList(hotWikis);
    }

    public void saveAll(List<HotWiki> hotWikis) {
        hotWikiRepository.saveAll(hotWikis);
    }
}
