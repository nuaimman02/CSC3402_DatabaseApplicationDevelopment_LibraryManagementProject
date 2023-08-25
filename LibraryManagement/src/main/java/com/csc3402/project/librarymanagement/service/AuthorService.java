package com.csc3402.project.librarymanagement.service;

import com.csc3402.project.librarymanagement.entity.Author;
import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    Page<Author> getAuthors(String keyword, int page, int size);

    Author getAuthor(Long id);

    Author addAuthor(Author author);

    void deleteAuthor(Long id);

    Author updateAuthor(Long id, Author author);

    List<Author> getAllAuthors();

    Page<Book> getAuthorBooks(Long id, String keyword, int page, int size);

}
