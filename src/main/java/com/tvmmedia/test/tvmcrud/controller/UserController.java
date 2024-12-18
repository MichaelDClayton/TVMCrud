package com.tvmmedia.test.tvmcrud.controller;


import com.tvmmedia.test.tvmcrud.dao.UserRepository;
import com.tvmmedia.test.tvmcrud.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    Collection<User> users() {
        log.info("retrieved all users!");
        return userRepository.findAll();
    }


    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/user")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create user: {}", user);
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
   /* @PostMapping("/user")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create user: {}", user);
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
                .body(result);
    }*/

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        log.info("Request to update user: {}", user);
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Request to delete user: {}", id);
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
