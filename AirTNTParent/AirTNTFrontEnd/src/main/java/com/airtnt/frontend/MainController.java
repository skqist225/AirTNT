package com.airtnt.frontend;

import java.util.List;

import com.airtnt.common.entity.Category;
import com.airtnt.frontend.category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public String index(Model model) {
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        return "index";
    }
}
