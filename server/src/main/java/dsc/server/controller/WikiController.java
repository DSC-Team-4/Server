package dsc.server.controller;

import dsc.server.dto.SearchRequest;
import dsc.server.dto.SearchResponse;
import dsc.server.dto.WikiRequest;
import dsc.server.entity.Wiki;
import dsc.server.service.WikiService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WikiController {
    private final WikiService wikiService;

    @PostMapping("/save")
    public String save(@RequestBody WikiRequest request) {
        Wiki newWiki = new Wiki(request.title(), request.country(), request.url(), LocalDateTime.now());
        Long savedWikiId = wikiService.save(newWiki);
        return "/index";
    }

    @GetMapping("/all-data")
    @ResponseBody
    public List<SearchResponse> getWikiList(@ModelAttribute SearchRequest searchDto, Model model) {
        List<Wiki> resultWikis = wikiService.getAll();

        return SearchResponse.ofList(resultWikis);
    }
}