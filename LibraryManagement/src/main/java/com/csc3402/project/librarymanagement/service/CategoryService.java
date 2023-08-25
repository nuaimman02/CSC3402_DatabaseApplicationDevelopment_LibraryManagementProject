package com.csc3402.project.librarymanagement.service;

import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> getCategories(String keyword, int page, int size);

    Category getCategory(Long id);

    void deleteCategory(Long id);

    void saveCategory(Category category);

    void updateCategory(Long id, Category category);

    List<Category> getAllCategories();

    Page<Book> getCategoryBooks(Long id, String keyword, int page, int size);
}
