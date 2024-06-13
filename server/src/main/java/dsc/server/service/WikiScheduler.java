package dsc.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WikiScheduler {
    private final WikiService wikiService;

    // 1000(1초) * 60 = 1분
    @Scheduled(fixedRate = 1000 * 60)
    public void updateEWMA() {
        log.info("Updated Wiki EWMA");
    }
}
