package com.ma.bookies1.service;
import com.ma.bookies1.dto.UserShow;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<?> allUsers() {
        // List<User> users = new ArrayList<>();

        return userRepository.findAllUserWithTheirRoles();


    }

    public List<?> allBooks() {
        return userRepository.findAllBooksWithUsers();
    }
}