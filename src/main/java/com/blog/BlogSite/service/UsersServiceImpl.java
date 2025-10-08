package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.UsersDto;
import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService{
    private final UsersRepo usersRepo;

    @Autowired
    public UsersServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public Users save(UsersDto usersDto) {
        Users users = new Users();
        users.setEmail(usersDto.getEmail());
        users.setAuthority(usersDto.getAuthority());
        users.setPassword(new BCryptPasswordEncoder().encode(usersDto.getPassword()));
        return usersRepo.save(users);
    }
    @Override
    public Users findByEmail(String username) {
        return usersRepo.findByEmail(username);
    }

    @Override
    public Users getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return usersRepo.findByEmail(authentication.getName());
    }
}
