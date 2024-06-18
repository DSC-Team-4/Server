package dsc.server.controller;

import dsc.server.dto.HotWikiResponse;
import dsc.server.service.HotWikiService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotWikiRestController {
    private final HotWikiService hotWikiService;

    @GetMapping("/get-hot-wikis")
    public HotWikiResponse getHotWikis(
            @RequestParam("startTime") LocalDateTime startTime,
            @RequestParam("endTime") LocalDateTime endTime
    ) {
        return hotWikiService.findByTimeBetween(startTime, endTime);
    }
}
