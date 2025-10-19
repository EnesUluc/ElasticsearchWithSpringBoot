package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.CommentDto;
import com.blog.BlogSite.entity.Comment;
import com.blog.BlogSite.entity.UserBlog;
import com.blog.BlogSite.entity.Users;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentDto commentDto);
    List<CommentDto> findAllBlogComments(int blogId);
    void deleteComment(int commentId);
    int findBlogIdByCommentId(int commentId);
    void deleteAllByUser(Users user);
}
