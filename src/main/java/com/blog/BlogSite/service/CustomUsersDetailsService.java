package com.blog.BlogSite.service;


import com.blog.BlogSite.config.CustomUsersDetails;
import com.blog.BlogSite.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    private final UsersService usersService;

    @Autowired
    public CustomUsersDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }


    // Find the user using your CustomUserDetails class
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersService.findByEmail(username);
        return new CustomUsersDetails(users);
    }
}
