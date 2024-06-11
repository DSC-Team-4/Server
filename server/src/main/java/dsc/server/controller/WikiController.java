package dsc.server.controller;

import dsc.server.dto.SearchRequest;
import dsc.server.dto.WikiResponse;
import dsc.server.service.WikiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class WikiController {
    private final WikiService wikiService;

    @GetMapping("/search")
    public String search(@ModelAttribute SearchRequest request, Model model) {
        List<WikiResponse> findWikiResponses = wikiService.findByFilter(request.search(), request.period(), request.country());

        model.addAttribute("wikis", findWikiResponses);

        return "index";
    }
}
