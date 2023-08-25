package com.csc3402.project.librarymanagement.repository;

import com.csc3402.project.librarymanagement.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
}