package com.csc3402.project.librarymanagement.service.impl;

import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Borrow;
import com.csc3402.project.librarymanagement.entity.security.User;
import com.csc3402.project.librarymanagement.exceptions.BookNotFoundException;
import com.csc3402.project.librarymanagement.repository.BookRepository;
import com.csc3402.project.librarymanagement.repository.BorrowRepository;
import com.csc3402.project.librarymanagement.repository.UserRepository;
import com.csc3402.project.librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Book> getBooks(String keyword, int page, int size) {
        keyword = "%" + keyword + "%";
        log.info(keyword);
        return bookRepository.findBooksByName(keyword, PageRequest.of(page, size));
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public boolean borrowBook(Long id, String date) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id " + id + " not found"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(date, dateTimeFormatter);
        Period period = Period.ofMonths(3);
        if (date1.isBefore(LocalDate.now().plus(period))){
            Borrow borrow = new Borrow();
            borrow.setUser(getUser());
            borrow.setBook(book);
            borrow.setReturned(false);
            borrow.setBorrowedAt(LocalDate.now());
            borrowRepository.save(borrow);
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIfAlreadyBorrowed(Long id) {
        return borrowRepository.findIfAlreadyBorrowed(id, getUser().getId()).isPresent();
    }

    @Override
    public boolean checkBookQuantity(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id " + id + " not found"));
        return book.getQuantity() != 0;
    }

    @Override
    public Page<Borrow> getBorrowedBooks(String keyword, int page, int size) {
        keyword = keyword + "%";
        return borrowRepository.findBorrowedBooks(getUser().getId(),keyword, PageRequest.of(page, size));
    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName).get();
    }

    @Override
    public void returnBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id " + id + " not found"));
        Borrow borrow = borrowRepository
                .findIfAlreadyBorrowed(book.getId(), getUser().getId())
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + id + " is not borrowed"));
        borrow.setReturned(true);
        borrow.setReturnedAt(LocalDate.now());
        borrowRepository.save(borrow);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBook(Long id, Book book) {
        bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id : " + id + " not found"));
        bookRepository.save(book);
    }

}
