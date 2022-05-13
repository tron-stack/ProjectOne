package com.revature.test;

import com.revature.dao.IUserDao;
import com.revature.exceptions.UsernameOrPasswordIncorrectException;
import com.revature.models.User;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
	@Mock
	static IUserDao iud;
	@InjectMocks
	static UserService us;

	@Before
	public void initUserTests() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void registerUser() {
		User u = new User("cp","password","Chime","Walden","cp@gmail.com",1);

		doNothing().when(iud).createUser(any());
		// setup user passed to method
		us.registerUser(u);
		verify(iud).createUser(any());
	}


	@Test
	public void loginUser() {


		User u = new User(50, "test", "password", "testName", "testLast", "test@email", 1);
		doReturn(u).when(iud).getUserByUsername(any());


		us.loginUser(u.getUserName(), u.getPassword());

		verify(iud).getUserByUsername(any());

	}

	@Test
	public void getAllUser() {

		List<User> list = new ArrayList<>();
		doReturn(list).when(iud).getAllUsers();

		us.getAllUser();

		verify(iud).getAllUsers();

		//when(iud.getAllUser(any())).
	}

	@Test
	public void getUserByUsername() {


		User u = new User(50, "test", "password", "testName", "testLast", "test@email", 1);

		doReturn(u).when(iud).getUserByUsername(u.getUserName());

		us.getUserByUsername(u.getUserName());

		verify(iud).getUserByUsername(any());

	}

	@Test
	public void testRegisterUser() {
		User u = new User("cp","password","Chime","Walden","cp@gmail.com",1);

		doNothing().when(iud).createUser(u);

		us.registerUser(u);

		verify(iud).createUser(u);
	}

	@Test
	public void testGetUserById() {
		User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);

		doReturn(u).when(iud).getUserById(u.getUserID());

		us.getUserById(u.getUserID());

		verify(iud).getUserById(u.getUserID());
	}

	@Test
	public void testGetUserByUsername() {
		User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);

		doReturn(u).when(iud).getUserByUsername(u.getUserName());

		us.getUserByUsername(u.getUserName());

		verify(iud).getUserByUsername(u.getUserName());
	}

	@Test
	public void testReadUserList() {
		List<User> list = new ArrayList<>();
		User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);
		list.add(u);

		doReturn(list).when(iud).getAllUsers();

		us.readUserList();
		verify(iud).getAllUsers();

	}

	@Test
	public void testUpdateUser() {
		User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);

		doReturn(u).when(iud).updateUser(u);

		us.updateUser(u);

		verify(iud).updateUser(u);
	}

	@Test
	public void testLoginUser() {
		User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);

		doReturn(u).when(iud).getUserByUsername(any());

		User user = us.loginUser("cp", "password");
		verify(iud).getUserByUsername(any());

		// AssertEquals to match properties of the user
		// message, expected and actual
		assertEquals("The first name should be Chime", "Chime", user.getFirstName());
	}

	@Test
	public void testWrongUsername() throws UsernameOrPasswordIncorrectException {

		doReturn(null).when(iud).getUserByUsername(any());

		User credentials = us.loginUser("cp", "password");
		verify(iud).getUserByUsername(any());
	}

	@Test
	public void testPassword() throws UsernameOrPasswordIncorrectException{
		User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);

		doReturn(u).when(iud).getUserByUsername(any());

		User credentials = us.loginUser("cp", "pass");
		verify(iud).getUserByUsername(any());
	}
}