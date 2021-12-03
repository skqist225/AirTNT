package com.airtnt.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/header")
    public String testViewHeader() {

        return "header";
    }
}
