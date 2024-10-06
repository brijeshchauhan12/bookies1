package com.ma.bookies1.controller.bookController;



import com.ma.bookies1.dto.BookDto;
import com.ma.bookies1.dto.bookDto2;
import com.ma.bookies1.entity.book.Book;
import com.ma.bookies1.service.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ma.bookies1.error.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        try {
            System.out.println(bookDto.toString()+"book dto controller");
            Book savedBook = bookService.saveBook(bookDto);
            System.out.println(savedBook.toString()+"saved book in controller");
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            System.err.println("Data integrity violation: " + e.getMessage());
            // Log more details if necessary

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IllegalArgumentException e){
            System.err.println("Illegal Argument: " + e.getMessage());

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            // Log stack trace or more details if necessary
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooksByAuthenticatedUser();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Integer id) {
        try {
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id) {
        try {
            Book updatedBook = bookService.updateBook(id);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("updatebookdetails/{id}")
    public ResponseEntity<?> updateBookDetails(@PathVariable Integer id, @RequestBody BookDto bookDto) {
        try {
            Book updatedBook = bookService.updateBookDetails(id,bookDto);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

