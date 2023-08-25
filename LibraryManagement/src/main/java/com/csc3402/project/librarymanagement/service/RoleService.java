package com.csc3402.project.librarymanagement.service;

import com.csc3402.project.librarymanagement.entity.security.Authority;
import com.csc3402.project.librarymanagement.entity.security.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    Role saveRole(Role role);

    void addAuthoritiesToRole(UUID uuid, List<Authority> authorities);
}
