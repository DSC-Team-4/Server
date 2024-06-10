package dsc.server.controller;

import dsc.server.dto.SearchDto;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WikiController {
    private final WikiService wikiService;

    @PostMapping("/save")
    public String save(@RequestParam("title") String title) {
        Wiki newWiki = new Wiki(title, LocalDateTime.now());
        return wikiService.save(newWiki);
    }

    @GetMapping("/search")
    public String getWikiList(@ModelAttribute SearchDto searchDto, Model model) {
        System.out.println(searchDto);
        model.addAttribute("result", "hi");
        return "/index";
    }
}
