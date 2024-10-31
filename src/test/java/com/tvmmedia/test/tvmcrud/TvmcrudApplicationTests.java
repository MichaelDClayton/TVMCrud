package com.tvmmedia.test.tvmcrud;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tvmmedia.test.tvmcrud.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TvmcrudApplicationTests {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestRestTemplate restTemplate;


    private final Logger log = LoggerFactory.getLogger(TvmcrudApplicationTests.class);


    @After
    public void after() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS user");
    }


    @Test
    public void testCreateUser() {
        log.info("Testing Create User");
        User user = new User();
        user.setId(Long.valueOf(2102));
        user.setEmail("mc@aol.com");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");
        final ResponseEntity<User> response = restTemplate.postForEntity("/api/user", user, User.class);
        Assertions.assertEquals(201, response.getStatusCode().value());
        User savedUser = response.getBody();
        Assertions.assertNotNull(savedUser.getId());

        log.info("Deleting user with id {}", savedUser.getId());
        restTemplate.delete("/api/user/" + savedUser.getId());
        log.info("Making request for deleted User with id {}", savedUser.getId());
        ResponseEntity<User> responseForDeletedUser = restTemplate.getForEntity("/api/user/" + savedUser.getId(), User.class, savedUser);
        User deletedUser = responseForDeletedUser.getBody();
        Assertions.assertNull(deletedUser);
    }

    @Test
    public void testCreateAndUpdateUser() {
        log.info("Testing Update User");
        User user = new User();
        user.setId(Long.valueOf(2102));
        user.setEmail("mc@aol.com");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");

        ResponseEntity<User> response = restTemplate.postForEntity("/api/user", user, User.class);
        Assertions.assertEquals(201, response.getStatusCode().value());
        User savedUser = response.getBody();
        Assertions.assertNotNull(savedUser.getId());

        log.info("Updating user with id {} to email: {}", savedUser.getId(), "updated@aol.com");
        savedUser.setEmail("updated@aol.com");
        restTemplate.put("/api/user/" + savedUser.getId(), savedUser);

        log.info("Making request for updated user");
        ResponseEntity<User> updateResonse = restTemplate.getForEntity("/api/user/" + savedUser.getId(), User.class, savedUser);
        User updatedUser = updateResonse.getBody();
        Assertions.assertEquals("updated@aol.com", updatedUser.getEmail());

        log.info("Deleting user with id {}", updatedUser.getId());
        restTemplate.delete("/api/user/" + updatedUser.getId());
        log.info("Making request for deleted User with id {}", updatedUser.getId());
        ResponseEntity<User> responseForDeletedUser = restTemplate.getForEntity("/api/user/" + updatedUser.getId(), User.class, savedUser);
        User deletedUser = responseForDeletedUser.getBody();
        Assertions.assertNull(deletedUser);
    }


    @Test
    public void testCreateAndGetAllUsers() throws JSONException {
        log.info("Creating User 1 for testCreateAndGetAllUsers");
        User user = new User();
        user.setEmail("mc@aol.com");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");
        ResponseEntity<User> response = restTemplate.postForEntity("/api/user", user, User.class);
        Assertions.assertEquals(201, response.getStatusCode().value());

        log.info("Creating User 2 for testCreateAndGetAllUsers");
        user = new User();
        user.setEmail("player@nba.com");
        user.setFirstName("Patrick");
        user.setLastName("Ewing");
        response = restTemplate.postForEntity("/api/user", user, User.class);
        Assertions.assertEquals(201, response.getStatusCode().value());

        log.info("Making request to get all users");
        ResponseEntity<String> responseForGetAllUsers = restTemplate.getForEntity("/api/users", String.class);
        JSONArray accounts = new JSONArray(responseForGetAllUsers.getBody());
        List<User> userList = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<User>>() {
        }.getType());
        Assertions.assertEquals(200, responseForGetAllUsers.getStatusCode().value());
        Assertions.assertEquals(2, userList.size());

        log.info("Deleting user with id {}", userList.get(0).getId());
        restTemplate.delete("/api/user/" + userList.get(0).getId());
        log.info("Making request for deleted User with id {}", userList.get(0).getId());
        ResponseEntity<User> responseForDeletedUser = restTemplate.getForEntity("/api/user/" + userList.get(0).getId(), User.class, userList.get(0));
        User deletedUser = responseForDeletedUser.getBody();
        Assertions.assertNull(deletedUser);

        log.info("Deleting user with id {}", userList.get(1).getId());
        restTemplate.delete("/api/user/" + userList.get(1).getId());
        log.info("Making request for deleted User with id {}", userList.get(1).getId());
        responseForDeletedUser = restTemplate.getForEntity("/api/user/" + userList.get(1).getId(), User.class, userList.get(1));
        deletedUser = responseForDeletedUser.getBody();
        Assertions.assertNull(deletedUser);


    }

}
