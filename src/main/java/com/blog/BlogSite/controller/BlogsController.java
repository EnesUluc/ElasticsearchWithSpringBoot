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


    @GetMapping("/edit-blogs")
    public String editBlog(Model model){
        model.addAttribute("blog", new BlogDto());
        return "blog-edit";
    }

    @PostMapping("/edit-blogs")
    public String createBlog(BlogDto blogDto){

        // burayı toparla metot içini temizle biraz.s
        Users user = usersService.getCurrentUserProfile();

        UserBlog userBlog = new UserBlog();
        userBlog.setUser(user);
        user.getBlogs().add(userBlog);

        UserBlog savedUserBlog = userBlogService.save(userBlog);

        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setText(blogDto.getText());
        blog.setBlogId(savedUserBlog.getBlogId());
        blog.setUserId(user.getUserId());



        blogService.saveBlog(blog);

        // now you can save and set user of blog. ***********************
        return "redirect:/blogs";
    }
}
