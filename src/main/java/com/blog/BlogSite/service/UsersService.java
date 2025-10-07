package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.UsersDto;
import com.blog.BlogSite.entity.Users;


public interface UsersService {
    Users save(UsersDto usersDto);
    Users findByEmail(String username);
}
