package com.blog.BlogSite.controller;

import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.dto.CommentDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.entity.UserBlog;
import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.service.BlogService;
import com.blog.BlogSite.service.CommentService;
import com.blog.BlogSite.service.UserBlogService;
import com.blog.BlogSite.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blogs/comments")
public class CommentsController {
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

        //Önce bir comment yap  kontrol et dbde.  şimdi artık commentleri listele isimleri içerikleriyşle felan.
        CommentDto comment = new CommentDto();
        comment.setBlog(userBlogService.findById(blogId));
        comment.setUser(usersService.getCurrentUserProfile());


        List<CommentDto> blogComments = commentService.findAllBlogComments(blogId);

        model.addAttribute("blogComments", blogComments);
        model.addAttribute("comment", comment);
        model.addAttribute("theUser", usersService.getCurrentUserProfile());

        return "blog-comments";
    }

    @PostMapping("/add-comment")
    public String addComment(CommentDto commentDto){
        commentService.saveComment(commentDto);
        return "redirect:/blogs/comments/"+commentDto.getBlogId();
    }

    @GetMapping("/remove-comment/{id}")
    public String removeComment(@PathVariable("id") int commentId){
        int blogId = commentService.findBlogIdByCommentId(commentId);
        commentService.deleteComment(commentId);
        return "redirect:/blogs/comments/"+blogId;
    }

}
