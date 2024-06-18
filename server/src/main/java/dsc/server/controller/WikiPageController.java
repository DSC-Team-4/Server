package dsc.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WikiPageController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/country")
    public String country() {
        return "country";
    }

    @GetMapping("/period")
    public String period() {
        return "period";
    }
}
