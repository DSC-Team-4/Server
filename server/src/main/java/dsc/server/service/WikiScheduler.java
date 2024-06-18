package dsc.server.service;

import dsc.server.entity.HotWiki;
import dsc.server.entity.Wiki;
import dsc.server.repository.HotWikiRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WikiScheduler {
    private final WikiService wikiService;
    private final HotWikiRepository hotWikiRepository;

    /*
     * 1000 * 60 = 1분
     * 1000 * 60 * 60 = 1시간
     */
    @Scheduled(fixedDelay = 1000 * 60, zone = "Asia/Seoul")
    public void saveHotWikis() {
        log.info("Saving hot-wikis");

        final int topMaxCount = 10;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeHoursAgo = now.minusHours(3);

        List<Wiki> wikis = wikiService.findByEditedAtBetween(threeHoursAgo, now);

        List<Wiki> hotWikis = wikis.stream()
                .sorted(Comparator.comparingDouble(Wiki::getEwma))
                .limit(topMaxCount)
                .toList();

        LocalDateTime dbDate = now.plusHours(9);
        hotWikiRepository.saveAll(HotWiki.ofList(hotWikis, dbDate));

        log.info("found Wiki data size: {}", wikis.size());
        log.info("saved HotWiki data size: {}", hotWikis.size());
    }
}
