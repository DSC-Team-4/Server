package dsc.server.controller;

import dsc.server.dto.HotWikiResponse;
import dsc.server.dto.WikiResponse;
import dsc.server.entity.HotWiki;
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
    public String search(
            Model model,
            @RequestParam("country") String country,
            @RequestParam(value = "maxCount") int maxCount
    ) {
        List<WikiResponse> result = wikiService.findByFilter(country, maxCount);

        model.addAttribute("wikis", result);

        return "country :: #popularList";
    }

    @GetMapping("/get-hot-wikis")
    public String getHotWikis(
            Model model,
            @RequestParam("startTime") LocalDateTime startTime,
            @RequestParam("endTime") LocalDateTime endTime
    ) {
        LocalDateTime startDateTime = startTime.plusHours(9);
        LocalDateTime endDateTime = endTime.plusHours(9);

        List<HotWiki> result = hotWikiService.findByTimeBetween(startDateTime, endDateTime);

        model.addAttribute("wikis", result);

        return "period :: #popularList";
    }
}
