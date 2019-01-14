package Application.Controller;

import Application.Dal.Entity.User;
import Application.Logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController
{
    private UserLogic userLogic;

    @Autowired
    public UserController(UserLogic userLogic) { this.userLogic = userLogic; }

    /* Format:
     {
	     "email":"INSERT_EMAIL",
	     "password":"INSERT_PASSWORD"
     } */
    @PostMapping(path = "/register")
    public User addUser(@RequestBody User user)
    {
        return userLogic.addUser(user);
    }

    @GetMapping(path="")
    public Iterable<User> getAllUsers() {
        return userLogic.getAllUsers();
    }

    @GetMapping("/{userId:[0-9]+}")
    public Optional<User> getUser(@PathVariable int userId) {
        return userLogic.getUser(userId);
    }
}