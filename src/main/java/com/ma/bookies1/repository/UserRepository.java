package com.ma.bookies1.repository;
import com.ma.bookies1.dto.UserShow;
import com.ma.bookies1.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);


    @Query(value = "select u.id, email, full_name,address, city, country, phone_number, postal_code,sex,date_of_birth, rr.name from users u inner join user_roles r on r.user_id=u.id inner join role rr on rr.id=r.role_id", nativeQuery = true)
    List<?> findAllUserWithTheirRoles();
    @Query(value = "select * from books", nativeQuery = true)
    List<?> findAllBooksWithUsers();
}