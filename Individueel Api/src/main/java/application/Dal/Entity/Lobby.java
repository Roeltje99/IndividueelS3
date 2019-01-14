package application.Dal.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Lobby")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Lobby
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LobbyId;
    @JsonProperty("lobbyName")
    private String LobbyName;

    @OneToMany(mappedBy = "lobby")
    private List<User> Users;

    protected Lobby(){}

    public Lobby(String lobbyName, List<User> users) {
        LobbyName = lobbyName;
        Users = users;
    }

    public void addUserToLobby(User user) { Users.add(user); }

    public void removeUserFromLobby(User user) { Users.remove(user); }

    public int getLobbyId() {
        return LobbyId;
    }

    public void setLobbyId(int lobbyId) {
        LobbyId = lobbyId;
    }

    public String getLobbyName() { return LobbyName; }

    public void setLobbyName(String lobbyName) { LobbyName = lobbyName; }

    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }
}
