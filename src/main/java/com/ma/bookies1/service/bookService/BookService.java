package com.ma.bookies1.service.bookService;


import com.ma.bookies1.dto.BookDto;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.entity.book.Book;
import com.ma.bookies1.repository.BookRepository;
import com.ma.bookies1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("User not found" );
        }
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}