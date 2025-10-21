package com.blog.BlogSite.controller;

import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.service.BlogService;
import com.blog.BlogSite.service.CommentService;
import com.blog.BlogSite.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{
    private final UsersService usersService;
    private final BlogService blogService;
    private final CommentService commentService;

    @Autowired
    public AccountController(UsersService usersService, BlogService blogService, CommentService commentService){
        this.usersService = usersService;
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("")
    public String accountDetails(Model model){
        Users user = getUser();
        String email = user.getEmail();
        int blogCount = blogService.findAllByUserId(user.getUserId());

        model.addAttribute("email",email);
        model.addAttribute("blogCount", blogCount);
        model.addAttribute("userId", user.getUserId());

        return "account";
    }

    @GetMapping("/delete")
    public String accountDelete(@RequestParam("id")Integer userId){
        commentService.deleteAllByUser(usersService.findById(userId).orElse(null));
        usersService.deleteById(userId);
        blogService.deleteAllByUserId(userId);
        return "redirect:/?info";
    }

}
