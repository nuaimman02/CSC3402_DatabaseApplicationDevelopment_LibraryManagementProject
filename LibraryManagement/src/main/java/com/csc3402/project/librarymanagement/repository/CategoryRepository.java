package com.csc3402.project.librarymanagement.repository;

import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where lower(c.name) like lower(:name)")
    Page<Category> findByName(@Param("name") String name, Pageable pageable);

}
