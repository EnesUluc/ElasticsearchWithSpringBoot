package com.blog.BlogSite.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    @NotBlank(message = "Enter a valid email!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotNull(message = "is required!")
    @Size(min = 4,message = "is required (at least 4 character)")
    private String password;

    @NotNull
    private String authority;
}
