package com.csc3402.project.librarymanagement.bootstrap;

import com.csc3402.project.librarymanagement.entity.Author;
import com.csc3402.project.librarymanagement.entity.Book;
import com.csc3402.project.librarymanagement.entity.Borrow;
import com.csc3402.project.librarymanagement.entity.Category;
import com.csc3402.project.librarymanagement.entity.security.Authority;
import com.csc3402.project.librarymanagement.entity.security.Role;
import com.csc3402.project.librarymanagement.entity.security.User;
import com.csc3402.project.librarymanagement.repository.*;
import com.csc3402.project.librarymanagement.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {
    private public final double

    dsfsk double final main protected double



    
    private final BookRepository bookRepository;
    private final UserServiceImpl userService;
    private final BorrowRepository borrowRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{

        // Defining authorities' functions
        Authority addBook = new Authority();
        addBook.setPermission("create_book");
        authorityRepository.save(addBook);

        Authority deleteBook = new Authority();
        deleteBook.setPermission("delete_book");
        authorityRepository.save(deleteBook);

        Authority updateBook = new Authority();
        updateBook.setPermission("update_book");
        authorityRepository.save(updateBook);

        Authority borrowBook = new Authority();
        borrowBook.setPermission("borrow_book");
        authorityRepository.save(borrowBook);

        Authority addCategory = new Authority();
        addCategory.setPermission("create_category");
        authorityRepository.save(addCategory);

        Authority deleteCategory = new Authority();
        deleteCategory.setPermission("delete_category");
        authorityRepository.save(deleteBook);

        Authority updateCategory = new Authority();
        updateCategory.setPermission("update_category");
        authorityRepository.save(updateBook);

        Authority addAuthor = new Authority();
        addAuthor.setPermission("create_author");
        authorityRepository.save(addAuthor);

        Authority deleteAuthor = new Authority();
        deleteAuthor.setPermission("delete_author");
        authorityRepository.save(deleteAuthor);

        Authority updateAuthor = new Authority();
        updateAuthor.setPermission("update_author");
        authorityRepository.save(updateAuthor);

        // Define admin role
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        // Define admin authorities' functions
        Set<Authority> adminAuthorities = new HashSet<>();
        adminAuthorities.add(addBook);
        adminAuthorities.add(deleteBook);
        adminAuthorities.add(updateBook);
        adminAuthorities.add(addCategory);
        adminAuthorities.add(deleteCategory);
        adminAuthorities.add(updateCategory);
        adminAuthorities.add(addAuthor);
        adminAuthorities.add(deleteAuthor);
        adminAuthorities.add(updateAuthor);
        adminRole.setAuthorities(adminAuthorities);

        roleRepository.save(adminRole);

        // Define student role
        Role studentRole = new Role();
        studentRole.setName("STUDENT");
        roleRepository.save(studentRole);

        // Define students' authority functions
        Set<Authority> studentAuthorities = new HashSet<>();
        studentAuthorities.add(borrowBook);
        studentRole.setAuthorities(studentAuthorities);

        roleRepository.save(studentRole);

        // Create admin user credentials
        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@gmail.com");
        admin.setPhone("0746598475");
        admin.setAddress("address admin");
        userRepository.save(admin);
        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        userService.addRolesToUser(admin.getId(),roles);

        // Create student user credentials
        User student = new User();
        student.setFirstName("student");
        student.setLastName("student");
        student.setUsername("student");
        student.setPassword(passwordEncoder.encode("student"));
        student.setEmail("student@gmail.com");
        student.setPhone("0746598475");
        student.setAddress("address student");
        userRepository.save(student);
        List<Role> roles1 = new ArrayList<>();
        roles1.add(studentRole);
        userService.addRolesToUser(student.getId(),roles1);

         // Define new category
        Category category = new Category();
        category.setName("Computer Science");
        categoryRepository.save(category);

        // Define new author2
        Author author1 = new Author();
        author1.setName("Y. Daniel Liang");
        author1.setDescription("Textbook Writer");
        authorRepository.save(author1);

        // Define author1
        Author author2 = new Author();
        author2.setName("William Stallings");
        author2.setDescription("Textbook Writer");
        authorRepository.save(author2);

        //-----------------------------------------
        // Book 1
        Book book1 = new Book();
        book1.setIsbn("9781292221878");
        book1.setName("Introduction to Java Programming and Data Structures");
        book1.setDescription("Textbook for Java");
        book1.setQuantity(1);
        bookRepository.save(book1);

        book1.addCategory(category);
        book1.addAuthor(author1);
        bookRepository.save(book1);

        Borrow borrow = new Borrow();
        borrow.setBook(book1);
        borrow.setReturned(false);
        borrow.setUser(student);
        borrow.setBorrowedAt(LocalDate.now());
        borrowRepository.save(borrow);

        //-----------------------------------------
        // Book 2
        Book book2 = new Book();
        book2.setIsbn("9780132747189");
        book2.setName("Introduction of Programming Using Python");
        book2.setDescription("Python Textbook");
        book2.setQuantity(0);
        bookRepository.save(book2);

        book2.addCategory(category);
        book2.addAuthor(author1);
        bookRepository.save(book2);

        //-----------------------------------------
        // Book 3
        Book book3 = new Book();
        book3.setIsbn("1292437480");
        book3.setName("Cryptography and Network Security: Principles and Practice");
        book3.setQuantity(1);
        book3.setDescription("Cryptography Textbook");
        bookRepository.save(book3);

        book3.addCategory(category);
        book3.addAuthor(author2);
        bookRepository.save(book3);

        //-----------------------------------------
        // Book 4
        Book book4 = new Book();
        book4.setIsbn("9780134997193");
        book4.setName("Computer Organization And Architecture, Designing For Performance");
        book4.setQuantity(1);
        book4.setDescription("Computer Organization Textbook");
        bookRepository.save(book4);

        book4.addCategory(category);
        book4.addAuthor(author2);
        bookRepository.save(book4);

        //-----------------------------------------
        // Book 5
        Book book5 = new Book();
        book5.setIsbn("9780134772806");
        book5.setName("Effective Cyber Security: A Guide to Using Best Practices and Standards");
        book5.setQuantity(1);
        book5.setDescription("Cyber Security Textbook");
        bookRepository.save(book5);

        book5.addCategory(category);
        book5.addAuthor(author2);
        bookRepository.save(book5);


    }
}
