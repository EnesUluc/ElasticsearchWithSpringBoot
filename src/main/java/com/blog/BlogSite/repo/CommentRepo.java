package com.blog.BlogSite.repo;

import com.blog.BlogSite.entity.Comment;
import com.blog.BlogSite.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByBlog_BlogId(int blogId);
    void deleteAllByUser(Users users);
}
