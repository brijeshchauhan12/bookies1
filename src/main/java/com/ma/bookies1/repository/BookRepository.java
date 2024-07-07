package com.ma.bookies1.repository;




import com.ma.bookies1.entity.book.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query(value = "SELECT b.* FROM books b INNER JOIN users u ON b.user_id = u.id WHERE u.email = ?1", nativeQuery = true)
    List<Book> findAllByUser(String username);

    @Query(value = "SELECT b.* FROM books b INNER JOIN users u ON b.user_id = u.id WHERE b.id = ?1 and u.email = ?2", nativeQuery = true)
    Optional<Book> findByIdAndUserName(Integer id, String username);

    @Modifying
    @Query(value = "DELETE FROM books WHERE id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteByIdAndUser(Integer id, Integer user_id);

    @Query(value = "select b.* FROM books b WHERE b.listable=true ", nativeQuery = true)
    List<Book> finAllListableBooks();
}