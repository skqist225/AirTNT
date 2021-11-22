package com.airtnt.frontend.manage_room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ManageController {

    @GetMapping(value = "/manage-your-space/{userId}/details")
    public String getMethodName(@PathVariable("userId") Integer userId) {
        return new String("abc");
    }

}
