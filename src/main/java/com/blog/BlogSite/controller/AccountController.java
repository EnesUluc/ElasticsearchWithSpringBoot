package com.blog.BlogSite.controller;

import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.service.BlogService;
import com.blog.BlogSite.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final UsersService usersService;
    private final BlogService blogService;

    @Autowired
    public AccountController(UsersService usersService, BlogService blogService){
        this.usersService = usersService;
        this.blogService = blogService;
    }

    @GetMapping("")
    public String accountDetails(Model model){
        Users user = usersService.getCurrentUserProfile();
        String email = user.getEmail();
        int blogCount = blogService.findAllByUserId(user.getUserId());

        model.addAttribute("email",email);
        model.addAttribute("blogCount", blogCount);
        System.out.println("User id: "+user.getUserId());
        model.addAttribute("userId", user.getUserId());
        return "account";
    }

    @GetMapping("/delete")
    public String accountDelete(@RequestParam("id")Integer userId){
        usersService.deleteById(userId);
        blogService.deleteAllByUserId(userId);
        return "redirect:/?info";
    }
}
