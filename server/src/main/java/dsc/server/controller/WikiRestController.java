package dsc.server.controller;

import dsc.server.dto.WikiEwmaRequest;
import dsc.server.dto.WikiRequest;
import dsc.server.dto.WikiResponse;
import dsc.server.entity.NotEwmaWiki;
import dsc.server.entity.Wiki;
import dsc.server.service.WikiService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.datetime.DateFormatter;
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
        LocalDateTime editedAt = LocalDateTime.parse(request.editedAt());

        NotEwmaWiki newNotEwmaWiki = new NotEwmaWiki(request.title(), request.country(), request.uri(), request.metaId(), editedAt);
        return wikiService.save(newNotEwmaWiki);
    }

    @GetMapping("/all-data")
    public List<WikiResponse> getWikis() {
        List<Wiki> resultNotEwmaWikis = wikiService.getAll();

        return WikiResponse.ofList(resultNotEwmaWikis);
    }

    @GetMapping("/get-ewma-data")
    public List<WikiEwmaRequest> getWikisByEwma() {
        return wikiService.getWikisForUpdateEwma();
    }
}
