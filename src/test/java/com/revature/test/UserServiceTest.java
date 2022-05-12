package com.revature.test;

import com.revature.dao.IUserDao;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
	@Mock
	static IUserDao iud;
	@InjectMocks
	static UserService us;

	@Before
	public void init() {
		IUserDao iud = Mockito.mock(IUserDao.class);
		UserService us = Mockito.mock(UserService.class);
	}

	@Test
	public void registerUser() {

		final String id = "1";
		// setup user passed to method
		User user = mock(User.class);
		when(user.getUserID()).thenReturn(Integer.valueOf(id));
		when(user.getUserName()).thenReturn("username");


		// run method under test
		us.registerUser(user.getUserName(),user.getPassword(), user.getFirstName(), user.getLastName(),user.getEmail(),user.getUserRole());

		assertEquals(user.getUserName(), String.valueOf(user));

	}


	@Test
	public void loginUser() {
		User user = mock(User.class);

		us.loginUser(user.getUserName(),user.getPassword());
		when()
	}

	@Test
	public void getAllUser() {
		when(iud.getAllUser(any())).
	}

	@Test
	public void getUserByUsername() {
	}
}