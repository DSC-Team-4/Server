package dsc.server.service;

import dsc.server.dto.HotWikiResponse;
import dsc.server.dto.HotWikiResponse.HotWikiInfo;
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

    public HotWikiResponse findByTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        List<HotWiki> findWikis = hotWikiRepository.findByCreatedAtBetweenOrderByEwmaDesc(startTime, endTime);

        List<HotWikiInfo> hotWikiInfos = HotWikiInfo.ofList(findWikis);

        return new HotWikiResponse(startTime, endTime, hotWikiInfos);
    }

    private List<HotWikiResponse> getHotWikiResponses(List<HotWiki> findWikis) {
        Map<LocalDateTime, List<HotWiki>> hotWikis = findWikis.stream()
                .collect(Collectors.groupingBy(HotWiki::getCreatedAt));

        return HotWikiResponse.ofList(hotWikis);
    }
}
