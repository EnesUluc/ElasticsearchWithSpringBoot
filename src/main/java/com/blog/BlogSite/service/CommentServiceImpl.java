package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.CommentDto;
import com.blog.BlogSite.entity.Comment;
import com.blog.BlogSite.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo){
        this.commentRepo = commentRepo;
    }
    @Override
    public Comment saveComment(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setBlog(commentDto.getBlog());
        comment.setUser(commentDto.getUser());
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepo.save(comment);
    }

    @Override
    public List<CommentDto> findAllBlogComments(int blogId) {
        List<Comment> comments = commentRepo.findAllByBlog_BlogId(blogId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment: comments){
            CommentDto commentDto = new CommentDto();
            commentDto.setBlog(comment.getBlog());
            commentDto.setContent(comment.getContent());
            commentDto.setUser(comment.getUser());

            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
