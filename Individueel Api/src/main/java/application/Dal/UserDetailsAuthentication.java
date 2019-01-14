package application.Dal;

import application.Dal.Entity.JwtUser;
import application.Dal.Entity.User;
import application.Dal.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsAuthentication implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsAuthentication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new JwtUser(user.getUserId(), user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}