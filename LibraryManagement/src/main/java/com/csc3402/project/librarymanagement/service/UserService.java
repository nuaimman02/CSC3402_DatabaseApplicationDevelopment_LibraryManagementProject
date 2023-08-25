package com.csc3402.project.librarymanagement.service;

import com.csc3402.project.librarymanagement.entity.security.Role;
import com.csc3402.project.librarymanagement.entity.security.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    void UpdateUser(User user);

    void addRolesToUser(UUID uuid, List<Role> roles);

    User loadUserByUsername(String username);
}
