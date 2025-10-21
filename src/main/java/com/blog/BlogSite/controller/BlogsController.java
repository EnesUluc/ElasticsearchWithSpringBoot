package com.blog.BlogSite.controller;

import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.entity.UserBlog;
import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.log.LoggerFactory;
import com.blog.BlogSite.service.BlogService;
import com.blog.BlogSite.service.UserBlogService;
import com.blog.BlogSite.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogsController {
    private final UsersService usersService;
    private final UserBlogService userBlogService;
    private final BlogService blogService;
    private final LoggerFactory log = LoggerFactory.getInstance();

    @Autowired
    public BlogsController(UsersService usersService, UserBlogService userBlogService, BlogService blogService) {
        this.usersService = usersService;
        this.userBlogService = userBlogService;
        this.blogService = blogService;
    }

    @GetMapping("")
    public String dashboard(Model model){
        List<BlogDto> blogDtoList = blogService.findAllBlogsDto();
        model.addAttribute("blogList", blogDtoList);
        return "blogs";
    }

    @GetMapping("/create-blog")
    public String editBlog(Model model){
        model.addAttribute("blog", new BlogDto());
        return "blog-create";
    }

    @PostMapping("/create-blog")
    public String createBlog(@Valid @ModelAttribute("blog") BlogDto blogDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "blog-create";
        }
        Users user = usersService.getCurrentUserProfile();

        UserBlog userBlog = new UserBlog();
        userBlog.setUser(user);
        user.getBlogs().add(userBlog);
        UserBlog savedUserBlog = userBlogService.save(userBlog);

        int blogId = savedUserBlog.getBlogId();
        int userId = user.getUserId();
        blogService.saveBlogDto(blogDto, blogId, userId);

        log.writeLog(user.getEmail() + " created a blog with blog id -> "+blogId);

        return "redirect:/blogs";
    }

    @GetMapping("/edit-blogs")
    public String listYourBlogs(Model model){
        Users user = usersService.getCurrentUserProfile();
        List<Blog> blogList = blogService.findAllBlogsByUserId(user.getUserId());
        model.addAttribute("blogList", blogList);

        return "blog-edit";
    }

    @GetMapping("/edit-blogs/{id}")
    public String editYourBlog(@PathVariable("id") int blogId, Model model){
        Blog blog = blogService.findByBlogId(blogId);

        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(blogId);
        blogDto.setText(blog.getText());
        blogDto.setTitle(blog.getTitle());
        model.addAttribute("blogDto", blogDto);

        return "blog-update";
    }


    @PostMapping("/update-blog")
    public String updateYourBlog(@Valid BlogDto blogDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "blog-update";
        }
        int blogId = blogDto.getBlogId();
        Blog blog = blogService.findByBlogId(blogId);
        blog.setText(blogDto.getText() + " (Edited)");
        blog.setTitle(blogDto.getTitle());
        blog.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        blogService.saveBlog(blog);


        log.writeLog( "The blog is updated with id -> " + blogId);

        return "redirect:/blogs";
    }

    @GetMapping("/delete-blog")
    public String deleteBlog(@RequestParam("id") int blogId){
        blogService.deleteByBlogId(blogId);
        userBlogService.deleteByBlogId(blogId);
        log.writeLog("The blog is deleted with id -> "+ blogId);
        return "redirect:/blogs";
    }

    @GetMapping("/search")
    public String searchBlogs(@RequestParam("query") String query, Model model){
        if(query.isBlank()){ return "redirect:/blogs"; }

        List<BlogDto> blogs = blogService.searchByText(query);
        model.addAttribute("blogList", blogs);
        return "blogs";
    }
}
