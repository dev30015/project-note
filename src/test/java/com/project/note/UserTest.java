package com.project.note;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.project.note.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.note.entity.User;

@SpringBootTest
class UserTest {
	
	@Resource
	private UserRepository userRespo;

	@Test
	void listAllUsers() {
		List<User> users = userRespo.findAll();
		for(User user:users) {
			System.out.println(user);
		}
	}

	@Test
	void testFindUser() {
		long id = 1;
		Optional<User> user1 = userRespo.findById(id);
		System.out.println(user1);
	}

	@Test
	void addUser() {
		User user = new User();
		user.setUsername("testUser3");
		user.setEmail("test@test.com");
		user.setPassword("md5");
		userRespo.save(user);
	}
}
