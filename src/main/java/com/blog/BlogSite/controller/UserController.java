package com.blog.BlogSite.controller;

import com.blog.BlogSite.dto.UsersDto;
import com.blog.BlogSite.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired


    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new UsersDto());
        return "register";
    }

    @PostMapping("/register/new")
    public String registerNew(@Valid UsersDto usersDto){
        usersService.save(usersDto);
        return "redirect:/";
    }
}
