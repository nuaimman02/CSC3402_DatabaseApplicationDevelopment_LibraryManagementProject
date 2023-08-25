package com.csc3402.project.librarymanagement.service.impl;


import com.csc3402.project.librarymanagement.entity.security.Role;
import com.csc3402.project.librarymanagement.entity.security.User;
import com.csc3402.project.librarymanagement.repository.UserRepository;
import com.csc3402.project.librarymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        // TODO handle exception
        checkIfUserExists(user.getUsername());
        return userRepository.save(hashPassword(user));
    }

    @Override
    public void UpdateUser(User user) {
        User user1 = userRepository.findById(user.getId()).get();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user1);
    }

    @Override
    public void addRolesToUser(UUID uuid, List<Role> roles) {
        if(roles != null && roles.size()>0)  {
            User user = userRepository.findById(uuid).get();
            Set<String> roleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
            List<Role> rolesToAdd = roles.stream().filter(e -> !roleNames.contains(e.getName())).toList();

            user.getRoles().addAll(rolesToAdd);
            userRepository.save(user);
        }
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    private void checkIfUserExists(String username){
        userRepository.findByUsername(username);
    }

    private User hashPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
