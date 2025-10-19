package com.blog.BlogSite.dto;

import com.blog.BlogSite.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private String text;
    private String title;
    private String username;
    private int blogId;
    private int commentCount;
    private String createdAt;
}
