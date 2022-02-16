package com.example.securitydemo.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
