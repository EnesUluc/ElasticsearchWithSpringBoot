package com.blog.BlogSite.controller;

import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {
    @Autowired
    protected UsersService usersService;

    protected Users getUser(){
        return usersService.getCurrentUserProfile();
    }
}
