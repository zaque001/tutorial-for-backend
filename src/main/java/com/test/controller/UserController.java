package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.test.repository.UserRepository;
import com.test.user.Status;
import com.test.user.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            System.out.println("User Already exists!");
            return Status.USER_ALREADY_EXISTS;
        }
        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @PostMapping("login")
    public Status loginUser(@Valid @RequestBody User user) {
        if (userRepository.existsByUsernameAndPassword(user.getUsername(),user.getPassword())) {
            user.setLoggedIn(true);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }

    @PostMapping("logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @DeleteMapping("users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }


    @GetMapping("test")
    public String testApi() {
        return "ok";
    }

}