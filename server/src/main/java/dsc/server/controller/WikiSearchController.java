package dsc.server.controller;

import dsc.server.dto.WikiResponse;
import dsc.server.entity.HotWiki;
import dsc.server.entity.Wiki;
import dsc.server.service.HotWikiService;
import dsc.server.service.WikiService;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime
    ) {
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME).plusHours(9);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME).plusHours(9);

        List<HotWiki> result = hotWikiService.findByTimeBetween(startDateTime, endDateTime);

        model.addAttribute("wikis", result);

        return "period :: #popularList";
    }

    @GetMapping("/save-hot-wiki")
    @ResponseBody
    public void saveHotWikis() {
        final int topMaxCount = 10;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeHoursAgo = now.minusHours(3);

        LocalDateTime startDbDate = threeHoursAgo.plusHours(9);
        LocalDateTime endDbDate = now.plusHours(9);

        List<Wiki> wikis = wikiService.findByEditedAtBetween(startDbDate, endDbDate);

        List<Wiki> hotWikis = wikis.stream()
                .sorted(Comparator.comparingDouble(Wiki::getEwma))
                .limit(topMaxCount)
                .toList();

        LocalDateTime dbDate = now.plusHours(9);
        System.out.println("saved HotWikis size : " + hotWikis.size());
        hotWikiService.saveAll(HotWiki.ofList(hotWikis, dbDate));
    }
}
