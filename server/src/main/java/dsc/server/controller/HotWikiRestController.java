package dsc.server.controller;

import dsc.server.dto.HotWikiResponse;
import dsc.server.service.HotWikiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotWikiRestController {
    private final HotWikiService hotWikiService;

    @GetMapping("/get-hot-wikis")
    public List<HotWikiResponse> getHotWikis() {
        return hotWikiService.getAllHotWikis();
    }
}
