package com.csc3402.project.librarymanagement.service;

import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Borrow;
import org.springframework.data.domain.Page;

public interface BookService {

    void saveBook(Book book);

    void deleteBook(Long id);

    void updateBook(Long id, Book book);

    Page<Book> getBooks(String keyword, int page, int size);

    Book getBook(Long id);

    boolean borrowBook(Long id, String date);

    boolean checkIfAlreadyBorrowed(Long id);

    boolean checkBookQuantity(Long id);

    Page<Borrow> getBorrowedBooks(String keyword, int page, int size);

    void returnBook(Long id);
}
