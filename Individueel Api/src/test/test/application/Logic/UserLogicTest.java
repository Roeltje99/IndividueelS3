package application.Logic;

import application.Dal.Entity.Lobby;
import application.Dal.Entity.User;
import application.Dal.Repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserLogicTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserLogic userLogic;

    @Before
    public void SetUp() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testCreateUserValid()
    {
        User dummyLobby = new User("test", "123");
        User dummyLobbyName = new User("testLobby","123");

        when(userRepository.save(any(User.class))).thenReturn(dummyLobby);

        User createdLobby = userLogic.addUser(dummyLobbyName);

        Assert.assertNotNull(createdLobby);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUserInvalidEmail()
    {
        User dummyUserName = new User("","123");

        exception.expect(IllegalArgumentException.class);

        userLogic.addUser(dummyUserName);
    }

    @Test
    public void testCreateUserInvalidPassword()
    {
        User dummyUserName = new User("roelicus@gmail.com","");

        exception.expect(IllegalArgumentException.class);

        userLogic.addUser(dummyUserName);
    }

    @Test
    public void testGetAllUsers()
    {
        Iterable<User> users = new LinkedList<User>();
        ((LinkedList<User>) users).add(new User("test", "123"));
        ((LinkedList<User>) users).add(new User("testLobby","321"));

        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> createdUsers = userLogic.getAllUsers();

        Assert.assertNotNull(createdUsers);
    }

    @Test
    public void testGetUserValid()
    {
        User dummyLobby = new User("test", "1234567896");
        dummyLobby.setUserId(1);
        Optional<User> testUser = Optional.of(dummyLobby);

        when(userRepository.findById(1)).thenReturn(testUser);

        User createdLobby = userLogic.getUser(1).get();

        Assert.assertEquals(createdLobby.getUserId(), testUser.get().getUserId());
    }

    @Test
    public void testGetUserInvalid()
    {
        exception.expect(IllegalArgumentException.class);

        Optional<User> user = userLogic.getUser(2);
    }
}