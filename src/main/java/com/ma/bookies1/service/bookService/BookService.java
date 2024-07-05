package com.ma.bookies1.service.bookService;


import com.ma.bookies1.dto.BookDto;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.entity.book.Book;
import com.ma.bookies1.repository.BookRepository;
import com.ma.bookies1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Book saveBook(BookDto bookDto) {

        Optional<User> userOptional = userRepository.findById(bookDto.getUserid());

        System.out.println(userOptional+"USER FOUND");
        Book book =new Book();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            book.setUser(user);
            book.setAuthor(bookDto.getAuthor());
            book.setGrade(bookDto.getGrade());
            book.setTitle(bookDto.getTitle());
            System.out.println(book.toString()+"printing bookk");
            bookRepository.save(book);
            return book;
        } else {
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            throw new RuntimeException("User not found" );
        }
    }

    public List<Book> getAllBooksByAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails+"username");
        return (List<Book>) bookRepository.findAllByUser(userDetails.getUsername());
    }

    public Optional<Book> getBookById(Integer id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookRepository.findByIdAndUserName(id, userDetails.getUsername());
    }


    @Transactional
    public void deleteBookById(Integer id) {
        System.out.println(id+"id in service deleteBookById");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        bookRepository.deleteByIdAndUser(id, user.get().getId());
    }



}
