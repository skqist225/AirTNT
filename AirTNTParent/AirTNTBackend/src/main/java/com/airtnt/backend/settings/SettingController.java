package com.airtnt.backend.settings;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
    
    @GetMapping("/settings")
    public String index(){
        return "settings/settings.html";
    }
}
