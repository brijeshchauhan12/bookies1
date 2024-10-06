package com.ma.bookies1.service.bookService;
import com.ma.bookies1.dto.BookDto;
import com.ma.bookies1.dto.bookDto2;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.entity.book.Book;
import com.ma.bookies1.repository.BookRepository;
import com.ma.bookies1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        // Check if a book with the same title already exists
        Optional<Book> existingBook = bookRepository.findByTitleAndUserId(bookDto.getTitle(), bookDto.getUserid());
        if (existingBook.isPresent()) {
            // Throw an exception or handle it as per your application's requirement
            throw new IllegalArgumentException("Book with title '" + bookDto.getTitle() + "' already exists.");
        }
        Book book =new Book();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            book.setUser(user);
            book.setAuthor(bookDto.getAuthor());
            book.setGrade(bookDto.getGrade());
            book.setTitle(bookDto.getTitle());
            book.setBoard(bookDto.getBoard());
            book.setGenre(bookDto.getGenre());
            book.setCountry(bookDto.getCountry());
            book.setEdition(bookDto.getEdition());
            book.setDescription(bookDto.getDescription());
            book.setIsbn(bookDto.getIsbn());
            book.setLanguage(bookDto.getLanguage());
            book.setYear(bookDto.getYear());
            book.setPages(bookDto.getPages());
            book.setPublisher(bookDto.getPublisher());

            book.setQuantity(bookDto.getQuantity());
            book.setPrice(bookDto.getPrice());

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

    public Optional<Book> getBookByIdPublic(Integer id){
        return bookRepository.findById(id);
    }


    @Transactional
    public void deleteBookById(Integer id) {
        System.out.println(id+"id in service deleteBookById");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        bookRepository.deleteByIdAndUser(id, user.get().getId());
    }


    public Book updateBook(Integer bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setListable(true);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }



    public Book updateBookDetails(Integer bookId, BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        // Check if a book with the same title already exists
        System.out.println(bookDto.getUserid()+"user id"+bookDto.getTitle()+"title");
        Integer x = bookRepository.findByTitleAndUserIdUpdate(bookDto.getTitle(), bookDto.getUserid());
        // System.out.println(existingBook.toString());
        if (x>1) {
            // Throw an exception or handle it as per your application's requirement
            throw new IllegalArgumentException("Book with title '" + bookDto.getTitle() + "' already exists.");
        }

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setAuthor(bookDto.getAuthor());
            book.setGrade(bookDto.getGrade());
            book.setTitle(bookDto.getTitle());
            book.setBoard(bookDto.getBoard());
            book.setGenre(bookDto.getGenre());
            book.setCountry(bookDto.getCountry());
            book.setEdition(bookDto.getEdition());
            book.setDescription(bookDto.getDescription());
            book.setIsbn(bookDto.getIsbn());
            book.setLanguage(bookDto.getLanguage());
            book.setYear(bookDto.getYear());
            book.setPages(bookDto.getPages());
            book.setPublisher(bookDto.getPublisher());
            book.setQuantity(bookDto.getQuantity());
            book.setPrice(bookDto.getPrice());
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    // public List<Book> gtListableBook() {
    //     return bookRepository.finAllListableBooks();
    // }

    public Page<Book> gtListableBook(PageRequest pageRequest) {
        return bookRepository.gtListableBook(pageRequest);
    }

    // public List<Book> searchBooksByTitle(String title) {
    //     return bookRepository.findByTitleContaining(title);
    // }

    public List<Book> searchBooksBySearchText(String searchText) {
        return bookRepository.findBookBySearchText(searchText);

    }

}