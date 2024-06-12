package dsc.server.controller;

import dsc.server.dto.SearchResponse;
import dsc.server.dto.WikiRequest;
import dsc.server.entity.Wiki;
import dsc.server.service.WikiService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WikiRestController {
    private final WikiService wikiService;

    @PostMapping("/save")
    public Long save(@RequestBody WikiRequest request) {
        Wiki newWiki = new Wiki(request.title(), request.country(), request.uri(), LocalDateTime.now());
        return wikiService.save(newWiki);
    }

    @GetMapping("/all-data")
    public List<SearchResponse> getWikiList() {
        List<Wiki> resultWikis = wikiService.getAll();

        return SearchResponse.ofList(resultWikis);
    }
}
