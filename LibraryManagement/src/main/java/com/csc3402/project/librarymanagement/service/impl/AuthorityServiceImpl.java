package com.csc3402.project.librarymanagement.service.impl;

import com.csc3402.project.librarymanagement.entity.security.Authority;
import com.csc3402.project.librarymanagement.repository.AuthorityRepository;
import com.csc3402.project.librarymanagement.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

}
