package com.blog.BlogSite.service;

import co.elastic.clients.util.DateTime;
import com.blog.BlogSite.entity.UserBlog;
import com.blog.BlogSite.repo.UserBlogRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserBlogServiceImpl implements UserBlogService {
    private final UserBlogRepo userBlogRepo;

    @Autowired
    public UserBlogServiceImpl(UserBlogRepo userBlogRepo) {
        this.userBlogRepo = userBlogRepo;
    }


    @Override
    public UserBlog save(UserBlog userBlog) {
        userBlog.setCreatedAt(LocalDateTime.now());
        return userBlogRepo.save(userBlog);
    }

    @Override
    @Transactional
    public void deleteByBlogId(int blogId) {
        userBlogRepo.deleteByBlogId(blogId);
    }

    @Override
    public UserBlog findById(int blogId) {
        return userBlogRepo.findById(blogId).orElseThrow( ()-> new UsernameNotFoundException("User could not found!"));
    }
}
