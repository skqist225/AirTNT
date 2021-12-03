package com.airtnt.frontend.settings;

import java.util.List;

import com.airtnt.common.entity.AmentityCategory;
import com.airtnt.frontend.amentity.AmentityCategorySerivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
    @Autowired
    AmentityCategorySerivce service;

    @GetMapping("/settings")
    public String index(Model model) {
        List<AmentityCategory> amentityCategories = service.listAll();
        model.addAttribute("amentityCategories", amentityCategories);
        return "settings/settings.html";
    }
}
