package application.Logic;

import application.Dal.Entity.*;
import application.Dal.Repository.LobbyRepository;
import application.Wrappers.UserLobbyRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LobbyLogic
{
    private LobbyRepository lobbyRepository;
    private UserLogic userLogic;

    @Autowired
    public LobbyLogic(LobbyRepository lobbyRepository, UserLogic userLogic) {
        this.lobbyRepository = lobbyRepository;
        this.userLogic = userLogic;
    }

    public Lobby addLobby(Lobby lobby)
    {
        if(lobby.getLobbyName().equals(""))
        {
            throw new IllegalArgumentException("Lobby name cannot be empty");
        }
        return lobbyRepository.save(lobby);
    }

    public Iterable<Lobby> getAllLobbies() {
        return lobbyRepository.findAll();
    }

    public Optional<Lobby> getLobby(int lobbyId)
    {
        Optional<Lobby> lobby = lobbyRepository.findById(lobbyId);
        if (lobby.equals(Optional.empty())) { throw new IllegalArgumentException("Lobby not found"); }
        return lobby;
    }

    public String deleteLobby(int lobbyId) throws IllegalArgumentException {
        Lobby lobby = getLobby(lobbyId).get();
        List<User> users = lobby.getUsers();
        if(users != null)
        {
            for (User user : users) { user.setLobby(null); }
            lobby.setUsers(users);
        }
        lobbyRepository.save(lobby);
        lobbyRepository.delete(lobby);
        return "Succesfully deleted";
    }

    public Lobby updateLobbyOnUser(int lobbyId, int userId) throws IllegalArgumentException
    {
        Lobby lobby = getLobby(lobbyId).get();
        User user = userLogic.getUser(userId).get();
        user.setLobby(lobby);
        lobby.addUserToLobby(user);
        return lobbyRepository.save(lobby);
    }

    public Lobby removeUserFromLobby(UserLobbyRequestModel userLobbyRequestModel) throws IllegalArgumentException
    {
        Lobby lobby = getLobby(userLobbyRequestModel.lobbyId).get();
        User user = userLogic.getUser(userLobbyRequestModel.userId).get();
        if (user.equals(null)) { throw new IllegalArgumentException("Lobby not found"); }
        user.setLobby(null);
        lobby.removeUserFromLobby(user);
        return lobbyRepository.save(lobby);
    }
}
