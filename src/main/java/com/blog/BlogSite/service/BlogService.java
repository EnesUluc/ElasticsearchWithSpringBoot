package com.blog.BlogSite.service;

import com.blog.BlogSite.entity.Blog;
import java.util.List;
import java.util.Optional;

public interface BlogService {
    Blog saveBlog(Blog blog);
    List<Blog> findAllBlogs();
    Optional<Blog> findByBlogId(String blogId);
    void deleteBlog(String blogId);
}
