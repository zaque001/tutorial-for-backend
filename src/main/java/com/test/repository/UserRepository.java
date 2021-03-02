package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
}
