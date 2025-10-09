package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    private final BlogRepo blogRepo;

    @Autowired
    public BlogServiceImpl(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    @Override
    public Blog saveBlogDto(BlogDto blogDto, Integer blogId, Integer userId) {
        // Dto operation
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setText(blogDto.getText());
        blog.setBlogId(blogId);
        blog.setUserId(userId);

        return blogRepo.save(blog);
    }

    @Override
    public List<Blog> findAllBlogs() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Blog> blogPage = blogRepo.findAll(pageable);
        return blogPage.getContent();
    }

    @Override
    public List<BlogDto> findAllBlogsDto() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Blog> blogPage = blogRepo.findAll(pageable);

        List<BlogDto> blogDtos = new ArrayList<>();
        for(Blog blog: blogPage){
            BlogDto blogDto = new BlogDto();
            blogDto.setTitle(blog.getTitle());
            blogDto.setText(blog.getText());

            blogDtos.add(blogDto);
        }
        return blogDtos;
    }

    @Override
    public Optional<Blog> findByBlogId(String blogId) {
        return blogRepo.findById(blogId);
    }

    @Override
    public void deleteBlog(String blogId) {
        blogRepo.deleteById(blogId);
    }

    @Override
    public List<Blog> searchByTextWord(String text) {
        return blogRepo.searchByText(text);
    }
}
