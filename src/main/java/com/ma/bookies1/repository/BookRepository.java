package com.ma.bookies1.repository;




import com.ma.bookies1.entity.book.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT b.* FROM books b INNER JOIN users u ON b.user_id = u.id WHERE u.email = ?1", nativeQuery = true)
    List<Book> findAllByUser(String username);

    @Query(value = "SELECT b.* FROM books b INNER JOIN users u ON b.user_id = u.id WHERE b.id = ?1 and u.email = ?2", nativeQuery = true)
    Optional<Book> findByIdAndUserName(Integer id, String username);

    @Modifying
    @Query(value = "DELETE FROM books WHERE id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteByIdAndUser(Integer id, Integer user_id);

    // @Query(value = "select b.* FROM books b WHERE b.listable=true ", nativeQuery = true)
    // List<Book> finAllListableBooks();

    @Query(value = "select b.* FROM books b WHERE b.listable=true ",
            countQuery = "select count(*) FROM books b WHERE b.listable=true",
            nativeQuery = true)
    Page<Book> gtListableBook(PageRequest pageRequest);

    Page<Book> findByListable(Boolean listable,PageRequest pageRequest);

    @Query(value = "select b.* FROM books b WHERE b.listable=true and (b.title LIKE %?1% OR b.author LIKE %?1% OR b.grade LIKE %?1%) limit 10", nativeQuery = true)
    List<Book> findBookBySearchText(String searchText);


    Optional<Book> findByTitleAndUserId(String title, Integer userId);
    @Query(value = "select count(*) as cnt from books WHERE title = ?1 and user_id = ?2", nativeQuery = true)
    Integer findByTitleAndUserIdUpdate(String title, Integer userId);
}
