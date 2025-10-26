package com.blog.BlogSite.controller;

import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/elastic")
public class RestControllerElasticsearch {
    private final BlogService blogService;

    @Autowired
    public RestControllerElasticsearch(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/")
    public Blog createBlog(@RequestBody Blog blog){
        return blogService.saveBlog(blog);
    }

    @GetMapping("/list")
    public List<Blog> listAllBlogs(){
        return blogService.findAllBlogs();
    }
    @GetMapping("/list/{id}")
    public Blog findBlogById(@PathVariable("id") String id){
        Optional<Blog> blog = blogService.findByBlogId(id);

        return blog.isPresent() ? blog.get() : null;
    }


    @DeleteMapping("/delete/{id}")
    public Blog deleteBlogById(@PathVariable("id") String id){
        Blog blog = blogService.findByBlogId(id).orElse(null);
        blogService.deleteBlog(id);
        return blog;
    }
}
