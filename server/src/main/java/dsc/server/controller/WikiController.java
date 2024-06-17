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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class WikiController {
    private final WikiService wikiService;

    @GetMapping("/search")
    public List<WikiResponse> search(@ModelAttribute SearchRequest request) {
        return wikiService.findByFilter(request.period(), request.country());
    }
}
