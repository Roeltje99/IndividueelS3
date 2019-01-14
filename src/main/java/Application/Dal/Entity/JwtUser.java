package Application.Dal.Entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtUser extends org.springframework.security.core.userdetails.User
{
    private int userId;

    public JwtUser(int userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}