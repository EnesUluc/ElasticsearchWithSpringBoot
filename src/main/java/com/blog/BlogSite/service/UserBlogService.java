package com.blog.BlogSite.service;

import com.blog.BlogSite.entity.UserBlog;

public interface UserBlogService {
    UserBlog save(UserBlog userBlog);
    void deleteByBlogId(int blogId);
}
