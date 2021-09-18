package com.airtnt.backend.user;

import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.Role;
import com.airtnt.common.entity.State;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    
    @Autowired UserService service;

    @GetMapping("/users")
    public String listFirstPage(Model model){
        return listByPage(1, "firstName", "asc", null, model);
    }

    @GetMapping("/users/page/{pageNum}")
    public String listByPage(
        @PathVariable("pageNum") int pageNum,
        @Param("sortField") String sortField,
        @Param("sortDir") String sortDir,
        @Param("keyword") String keyword,
        Model model
    ){
        Page<User> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<User> listUsers = page.getContent();
		
		long startCount = (pageNum - 1) * UserService.USERS_PER_PAGE + 1;
		long endCount = startCount + UserService.USERS_PER_PAGE -1;
		if(endCount>page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc")?"desc":"asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
        
        return "users/users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model){

        List<Role> listRoles = service.listRoles();
        List<Country> listCountries = service.listCountries();
        List<State> listStates = service.listStates();
        List<City> listCities = service.listCities();

		
		User user = new User();
		user.setStatus(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("listCountries", listCountries);
		model.addAttribute("listStates", listStates);
		model.addAttribute("listCities", listCities);
		model.addAttribute("pageTitle", "Create New User");

        return "users/user_form";
    }
}
