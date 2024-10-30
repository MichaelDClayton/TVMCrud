package com.tvmmedia.test.tvmcrud;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tvmmedia.test.tvmcrud.dao.UserRepository;
import com.tvmmedia.test.tvmcrud.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TvmcrudApplicationTests {


	private User user;

	@Autowired
	UserRepository repository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private User savedUser = null;



	@After
	public void after() {
		jdbcTemplate.execute("DROP TABLE IF EXISTS user");
	}

	@Test
	@Order(1)
	public void test1CreateUser() throws JSONException {
		user = new User();
		user.setId(Long.valueOf(2102));
		user.setEmail("mc@aol.com");
		user.setFirstName("TestFirstName");
		user.setLastName("TestLastName");
		final ResponseEntity<User> response = restTemplate.postForEntity("/api/user", user, User.class);
		Assertions.assertEquals(201, response.getStatusCode().value());
		this.savedUser = response.getBody();
		Assertions.assertNotNull(this.savedUser.getId());

		ResponseEntity<String> response1 = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts = new JSONArray(response1.getBody());
		List<User> userList = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser = userList.getFirst();
		Assertions.assertEquals(200, response1.getStatusCode().value());
		Assertions.assertEquals("mc@aol.com", testUser.getEmail());


		ResponseEntity<String> response2 = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts2 = new JSONArray(response2.getBody());
		List<User> userList2 = new Gson().fromJson(accounts2.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser2 = userList2.getFirst();

		ResponseEntity<?> responseForGetUserById = restTemplate.getForEntity("/api/users/"+testUser2.getId(), Optional.class);
		Optional<User> retrievedUser = (Optional<User>) responseForGetUserById.getBody();
		Assertions.assertTrue(retrievedUser.isPresent());

		/////////////////////////////
		ResponseEntity<String> response3 = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts3 = new JSONArray(response3.getBody());
		List<User> userList3 = new Gson().fromJson(accounts3.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser3 = userList3.getFirst();

		ResponseEntity<?> responseForGetUserById2 = restTemplate.getForEntity("/api/users/"+testUser3.getId(), Optional.class);
		Optional<User> retrievedUser2 = (Optional<User>) responseForGetUserById2.getBody();
		Assertions.assertTrue(retrievedUser2.isPresent());

		/////////////////////////////

		ResponseEntity<String> response4 = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts4 = new JSONArray(response4.getBody());
		List<User> userList4 = new Gson().fromJson(accounts4.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser4 = userList4.getFirst();

		restTemplate.delete("/api/user/"+testUser4.getId());

		ResponseEntity<String> responseRetrieved5 = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accountsRetrieved5 = new JSONArray(responseRetrieved5.getBody());
		List<User> userListRetrieved5 = new Gson().fromJson(accountsRetrieved5.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		Assertions.assertTrue(userListRetrieved5.isEmpty());
	}

/*
	@Test
	@Order(2)
	public void test2GetUsers() throws JSONException {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts = new JSONArray(response.getBody());
		List<User> userList = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser = userList.getFirst();
		Assertions.assertEquals(200, response.getStatusCode().value());
		Assertions.assertEquals("mc@aol.com", testUser.getEmail());
	}

	@Test
	@Order(3)
	public void test3GetUserById() throws JSONException {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts = new JSONArray(response.getBody());
		List<User> userList = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser = userList.getFirst();

		ResponseEntity<?> responseForGetUserById = restTemplate.getForEntity("/api/users/"+testUser.getId(), Optional.class);
		Optional<User> retrievedUser = (Optional<User>) responseForGetUserById.getBody();
        Assertions.assertTrue(retrievedUser.isPresent());
    }

	@Test
	@Order(4)
	public void test4DeleteUser()throws JSONException {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accounts = new JSONArray(response.getBody());
		List<User> userList = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		User testUser = userList.getFirst();

		restTemplate.delete("/api/user/"+testUser.getId());

		ResponseEntity<String> responseRetrieved = restTemplate.getForEntity("/api/users", String.class);
		JSONArray accountsRetrieved = new JSONArray(responseRetrieved.getBody());
		List<User> userListRetrieved = new Gson().fromJson(accountsRetrieved.toString(), new TypeToken<ArrayList<User>>(){}.getType());
		Assertions.assertTrue(userListRetrieved.isEmpty());
	}*/

}
