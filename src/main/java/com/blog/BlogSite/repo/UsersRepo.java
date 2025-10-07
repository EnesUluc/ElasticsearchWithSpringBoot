package com.blog.BlogSite.repo;

import com.blog.BlogSite.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo  extends JpaRepository<Users, Integer> {
    Users findByEmail(String username);
}
