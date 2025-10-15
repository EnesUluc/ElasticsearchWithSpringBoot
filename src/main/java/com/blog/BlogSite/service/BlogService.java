package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.entity.Users;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    Blog saveBlogDto(BlogDto blogDto, Integer blogId, Integer userId);
    Blog saveBlog(Blog blog);
    List<Blog> findAllBlogs();
    List<BlogDto> findAllBlogsDto();
    Optional<Blog> findByBlogId(String blogId);
    Blog findByBlogId(int id);
    void deleteBlog(String blogId);
    int findAllByUserId(int userId);
    void deleteAllByUserId(int userId);
    void deleteByBlogId(int blogId);
    List<Blog> findAllBlogsByUserId(int userId);
    List<BlogDto> searchByText(String word);
}
