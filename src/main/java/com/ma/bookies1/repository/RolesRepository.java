package com.ma.bookies1.repository;
import com.ma.bookies1.entity.Role;
import com.ma.bookies1.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}