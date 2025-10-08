package com.blog.BlogSite.controller;

import com.blog.BlogSite.dto.UsersDto;
import com.blog.BlogSite.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,ste);
    }


    @GetMapping("")
    public String register(Model model){
        model.addAttribute("user",new UsersDto());
        return "register";
    }

    @PostMapping("/new")
    public String registerNew(@Valid @ModelAttribute("user") UsersDto usersDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        usersService.save(usersDto);
        return "redirect:/?success";
    }
}
