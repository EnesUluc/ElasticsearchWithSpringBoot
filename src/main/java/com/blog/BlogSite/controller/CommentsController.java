package com.blog.BlogSite.controller;

import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.dto.CommentDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.entity.Comment;
import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.service.BlogService;
import com.blog.BlogSite.service.CommentService;
import com.blog.BlogSite.service.UserBlogService;
import com.blog.BlogSite.service.UsersService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/blogs/comments")
public class CommentsController extends BaseController{
    private final BlogService blogService;
    private final UsersService usersService;
    private final UserBlogService userBlogService;
    private final CommentService commentService;

    @Autowired
    public CommentsController(BlogService blogService, UsersService usersService,
                              UserBlogService userBlogService, CommentService commentService){
        this.blogService = blogService;
        this.usersService = usersService;
        this.userBlogService = userBlogService;
        this.commentService = commentService;
    }

    // For comment page
    @GetMapping("/{id}")
    public String viewComments(@PathVariable("id")int blogId, Model model){
        Blog blog = blogService.findByBlogId(blogId);

        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(blogId);
        blogDto.setText(blog.getText());
        blogDto.setTitle(blog.getTitle());
        usersService.findById(blog.getUserId()).ifPresent(users -> blogDto.setUsername(users.getEmail()));
        model.addAttribute("blog", blogDto);

        CommentDto comment = new CommentDto();
        comment.setBlog(userBlogService.findById(blogId));
        comment.setUser(usersService.getCurrentUserProfile());


        List<CommentDto> blogComments = commentService.findAllBlogComments(blogId);

        model.addAttribute("blogComments", blogComments);
        model.addAttribute("comment", comment);
        model.addAttribute("theUser", getUser());

        return "blog-comments";
    }

    @PostMapping("/add-comment")
    public String addComment(@Valid @ModelAttribute("comment") CommentDto comment, BindingResult bindingResult, Model model){
        int blogId = comment.getBlogId();
        if(bindingResult.hasErrors()){
            return "redirect:/blogs/comments/"+blogId;
        }
        Comment savedComment = commentService.saveComment(comment);

        Users user = getUser();
        String username = user.getEmail();
        log.info("User: {} published a new comment -> Comment Id: {}", username, savedComment.getCommentId());
        return "redirect:/blogs/comments/"+blogId;
    }

    @GetMapping("/remove-comment/{id}")
    public String removeComment(@PathVariable("id") int commentId){
        int blogId = commentService.findBlogIdByCommentId(commentId);
        commentService.deleteComment(commentId);

        Users user = getUser();
        String username = user.getEmail();
        log.info("User: {} deleted a comment -> Comment Id: {}",username,  commentId);
        return "redirect:/blogs/comments/"+blogId;
    }

}
