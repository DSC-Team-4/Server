package dsc.server.controller;

import dsc.server.dto.NotWikiRequest;
import dsc.server.dto.SearchRequest;
import dsc.server.dto.WikiEwmaRequest;
import dsc.server.dto.WikiUpdateRequest;
import dsc.server.dto.WikiResponse;
import dsc.server.entity.NotEwmaWiki;
import dsc.server.entity.Wiki;
import dsc.server.service.WikiService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WikiRestController {
    private final WikiService wikiService;

    @GetMapping("/all-data")
    public List<WikiResponse> getWikis() {
        List<Wiki> resultNotEwmaWikis = wikiService.getAll();

        return WikiResponse.ofList(resultNotEwmaWikis);
    }

    @GetMapping("/get-ewma-data")
    public List<WikiEwmaRequest> getWikisByEwma() {
        return wikiService.getWikisForUpdateEwma();
    }

    @PostMapping("/update-ewma-data")
    public void saveWikis(@RequestBody List<WikiUpdateRequest> request) {
        wikiService.updateEwma(request);
    }

    @PostMapping("/save")
    public Long save(@RequestBody NotWikiRequest request) {
        NotEwmaWiki newNotEwmaWiki = new NotEwmaWiki(request.title(), request.country(), request.uri(), request.metaId(), request.editedAt());
        return wikiService.save(newNotEwmaWiki);
    }
}
