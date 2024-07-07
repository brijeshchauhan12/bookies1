package com.ma.bookies1.controller.bookController;

import com.ma.bookies1.entity.book.Book;
import com.ma.bookies1.service.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pub/book")
public class PublicBookController {

    @Autowired
    public BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.gtListableBook();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
