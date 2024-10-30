package com.tvmmedia.test.tvmcrud.config;

import com.tvmmedia.test.tvmcrud.dao.UserRepository;
import com.tvmmedia.test.tvmcrud.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;


/*@Component
class DBInitializer implements CommandLineRunner {

    private final UserRepository repository;

    public DBInitializer(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {

        User newUser = new User("Mike", "Clayton","me@aol.com");
        repository.save(newUser);
        Optional<User> savedUser = repository.findById(newUser.getId());
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("User found using findById("+newUser.getId()+");");
        savedUser.ifPresent(System.out::println);
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("All Users found using findAll();");
        repository.findAll().forEach(System.out::println);
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("*********************");
    }
}*/
