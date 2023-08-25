package com.csc3402.project.librarymanagement.service.impl;

import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Category;
import com.csc3402.project.librarymanagement.exceptions.CategoryNotFoundException;
import com.csc3402.project.librarymanagement.repository.BookRepository;
import com.csc3402.project.librarymanagement.repository.CategoryRepository;
import com.csc3402.project.librarymanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Override
    public Page<Category> getCategories(String keyword, int page, int size) {
        keyword = keyword + "%";
        return categoryRepository.findByName(keyword, PageRequest.of(page, size));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with id " + id + "doesn't exists"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long id, Category category) {
        categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with id : " + id + " not found"));
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Book> getCategoryBooks(Long id,String keyword,int page,int size) {
        keyword = keyword + "%";
        return bookRepository
                .findBooksByCategoryId(id, keyword, PageRequest.of(page, size));
    }
}
