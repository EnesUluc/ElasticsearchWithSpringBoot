package com.blog.BlogSite.service;

import com.blog.BlogSite.dto.UsersDto;
import com.blog.BlogSite.entity.Users;
import com.blog.BlogSite.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService{
    private UsersRepo usersRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepo usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Users save(UsersDto usersDto) {
        Users users = new Users();
        users.setEmail(usersDto.getEmail());
        users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        return usersRepo.save(users);
    }
}
