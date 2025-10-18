package com.blog.BlogSite.dto;

import com.blog.BlogSite.entity.UserBlog;
import com.blog.BlogSite.entity.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Users user;
    private UserBlog blog;
    private String content;
}
