package com.blog.BlogSite.repo;

import com.blog.BlogSite.entity.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlogRepo extends JpaRepository<UserBlog, Integer> {
    void deleteByBlogId(int blogId);
}
