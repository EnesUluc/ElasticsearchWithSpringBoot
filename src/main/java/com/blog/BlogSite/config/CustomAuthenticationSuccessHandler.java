package com.blog.BlogSite.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        String username = user.getUsername();
        log.info("User: {} logged in.", username);
        boolean hasRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Author"));
        if(hasRole){
            response.sendRedirect("/blogs");
        }else{
            response.sendRedirect("/refused");
        }
    }
}
