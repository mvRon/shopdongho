package com.vanlang.shopdongho;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping("/service")
    public String servicePage() {
        return "service/service";
    }
}
