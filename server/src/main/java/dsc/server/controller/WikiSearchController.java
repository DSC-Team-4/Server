package dsc.server.controller;

import dsc.server.dto.HotWikiResponse;
import dsc.server.dto.WikiResponse;
import dsc.server.service.HotWikiService;
import dsc.server.service.WikiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WikiSearchController {
    private final WikiService wikiService;
    private final HotWikiService hotWikiService;

    @GetMapping("/search")
    public List<WikiResponse> search(Model model,
                                     @RequestParam("country") String country,
                                     @RequestParam(value = "maxCount") int maxCount
    ) {
        List<WikiResponse> result = wikiService.findByFilter(country, maxCount);

        return wikiService.findByFilter(country, maxCount);
    }

    @GetMapping("/get-hot-wikis")
    public HotWikiResponse getHotWikis(
            Model model,
            @RequestParam("startTime") LocalDateTime startTime,
            @RequestParam("endTime") LocalDateTime endTime
    ) {
        return hotWikiService.findByTimeBetween(startTime, endTime);
    }
}
