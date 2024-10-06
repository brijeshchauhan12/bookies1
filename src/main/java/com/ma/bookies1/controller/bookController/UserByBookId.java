
package com.ma.bookies1.controller.bookController;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.entity.book.Book;
import com.ma.bookies1.service.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequestMapping("/api/pub/user")
public class UserByBookId {
    @Autowired
    public BookService bookService;
    @GetMapping("/{id}")
    public ResponseEntity<String> getUserByBookId(@PathVariable Integer id){
        Optional<Book> book = bookService.getBookByIdPublic(id);
        User user=book.get().getUser();
        String email= user.getEmail();
        String userName=user.getFullName();
        int userid=user.getId();

        String emailandName=email+"*"+userName+"*"+userid;

        return new ResponseEntity<>(emailandName, HttpStatus.OK);
    }
}

