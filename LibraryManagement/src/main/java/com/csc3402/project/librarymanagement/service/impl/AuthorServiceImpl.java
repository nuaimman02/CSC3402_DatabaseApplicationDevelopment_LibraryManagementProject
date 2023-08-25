package com.csc3402.project.librarymanagement.service.impl;

import com.csc3402.project.librarymanagement.entity.Author;
import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Category;
import com.csc3402.project.librarymanagement.exceptions.AuthorNotFoundException;
import com.csc3402.project.librarymanagement.repository.AuthorRepository;
import com.csc3402.project.librarymanagement.repository.BookRepository;
import com.csc3402.project.librarymanagement.repository.CategoryRepository;
import com.csc3402.project.librarymanagement.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private Object name;


    @Override
    public Page<Author> getAuthors(String keyword, int page, int size) {
        keyword = keyword + "%";
        log.info(keyword);
        return authorRepository.findByName(keyword, PageRequest.of(page, size));
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException("Author with id " + id + " not found"));
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException("Author with id " + id + " not found"));
        authorRepository.save(author);
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Page<Book> getAuthorBooks(Long id, String keyword, int page, int size) {
        keyword = "%" + keyword + "%";
        return bookRepository
                .findBooksByAuthorId(id, keyword, PageRequest.of(page, size));
    }

}


