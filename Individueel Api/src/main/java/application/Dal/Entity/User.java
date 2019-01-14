package application.Dal.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity(name="User")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "LobbyId")
    private Lobby lobby;

    protected User(){}

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User(int userId, String email, String password)
    {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Lobby getLobby()
    {
        return lobby;
    }

    public void setLobby(Lobby lobby)
    {
        this.lobby = lobby;
    }
}