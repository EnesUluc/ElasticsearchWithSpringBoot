package com.blog.BlogSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogsController {

    @GetMapping("/edit-blogs")
    public String editBlog(){
        return "blog-edit";
    }
}
