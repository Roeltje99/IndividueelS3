package Application.Logic;

import Application.Dal.Entity.User;
import Application.Dal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLogic
{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserLogic(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User addUser(User user)
    {
        if (user.equals(null) || user.getEmail().equals(null) || user.getEmail().equals("") || user.getPassword().equals(null) || user.getPassword().equals("")) {
            throw new IllegalArgumentException("Email and Password can not be empty");
        }
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() { return userRepository.findAll(); }

    public Optional<User> getUser(int userId)
    {
        Optional<User> user = userRepository.findById(userId);
        if (user.equals(Optional.empty())) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

    /*
    public Optional<User> loginUser(User user)
    {
        Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        if (userOpt.get() != null && userOpt.get().getPassword() == password) {
            return userOpt;
        }
        else {
            throw new IllegalArgumentException("Wrong email or password");
        }
    }
    */
}
