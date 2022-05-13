package com.revature.services;

import com.revature.dao.IUserDao;
import com.revature.exceptions.UsernameOrPasswordIncorrectException;
import com.revature.models.User;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @BeforeClass
    public static void setupBeforeClass(){
        System.out.println("This runs once before the entire class.");
    }

    @Before
    public void setupBeforeMethods(){
        System.out.println("This runs once before each method in this class.");
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDownAfterMethod(){
        System.out.println("This will run once after each method in this class.");
    }
    @Mock
    static IUserDao iud;

    @InjectMocks
    static UserService us;

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

        doNothing().when(iud).updateUser(u);

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
    public void testWrongUsername() throws UsernameOrPasswordIncorrectException{

        doReturn(null).when(iud).getUserByUsername(any());

        User credentials = us.loginUser("cp", "password");
    }

    @Test
    public void testPassword() throws UsernameOrPasswordIncorrectException{
        User u = new User(1,"cp","password","Chime","Walden","cp@gmail.com",1);

        doReturn(u).when(iud).getUserByUsername(any());

        User credentials = us.loginUser("cp", "pass");
    }
}
