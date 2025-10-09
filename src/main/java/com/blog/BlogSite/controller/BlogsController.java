package com.blog.BlogSite.controller;

import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.entity.UserBlog;
import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.service.BlogService;
import com.blog.BlogSite.service.UserBlogService;
import com.blog.BlogSite.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BlogsController {
    private final UsersService usersService;
    private final UserBlogService userBlogService;
    private final BlogService blogService;

    @Autowired
    public BlogsController(UsersService usersService, UserBlogService userBlogService, BlogService blogService) {
        this.usersService = usersService;
        this.userBlogService = userBlogService;
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public String dashboard(Model model){
        List<BlogDto> blogDtoList = blogService.findAllBlogsDto();
        model.addAttribute("blogList", blogDtoList);
        return "blogs";
    }

    @GetMapping("/edit-blogs")
    public String editBlog(Model model){
        model.addAttribute("blogList", blogService.findAllBlogsDto());
        return "blog-edit";
    }

    @PostMapping("/edit-blogs")
    public String createBlog(BlogDto blogDto){
        Users user = usersService.getCurrentUserProfile();

        // Create a user blog
        UserBlog userBlog = new UserBlog();
        userBlog.setUser(user);
        user.getBlogs().add(userBlog);
        UserBlog savedUserBlog = userBlogService.save(userBlog);


        int blogId = savedUserBlog.getBlogId();
        int userId = user.getUserId();
        blogService.saveBlogDto(blogDto, blogId, userId);

        return "redirect:/blogs";
    }
}
