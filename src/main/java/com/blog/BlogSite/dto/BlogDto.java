package com.blog.BlogSite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    @NotBlank(message = "Please enter a text!")
    private String text;

    @NotBlank(message = "Please enter a title!")
    private String title;

    private String username;
    private int blogId;
    private int commentCount;
    private String createdAt;
}
