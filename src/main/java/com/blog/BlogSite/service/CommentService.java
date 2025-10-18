package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.CommentDto;
import com.blog.BlogSite.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentDto commentDto);
    List<CommentDto> findAllBlogComments(int blogId);
}
