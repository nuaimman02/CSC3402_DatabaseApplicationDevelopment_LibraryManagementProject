package com.csc3402.project.librarymanagement.service.impl;

import com.csc3402.project.librarymanagement.entity.security.User;
import com.csc3402.project.librarymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByUsername(username);
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(),user.getPassword(),user.getAuthorities());
        return userDetails;
    }
}
