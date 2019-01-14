package Application.Controller;

import Application.Dal.Entity.Lobby;
import Application.Logic.LobbyLogic;
import Application.Wrappers.UserLobbyRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/lobby")
public class LobbyControler
{
    private LobbyLogic lobbyLogic;

    @Autowired
    public LobbyControler(LobbyLogic lobbyLogic) {
        this.lobbyLogic = lobbyLogic;
    }

    /* Format:
     {
	     "lobbyName":"INSERT_LOBBY_NAME"
     } */
    @PostMapping(path = "")
    public Lobby addLobby(@RequestBody Lobby lobby) {
        return lobbyLogic.addLobby(lobby);
    }

    @GetMapping(path = "")
    public Iterable<Lobby> getAllLobbies() {
        return lobbyLogic.getAllLobbies();
    }

    @GetMapping(path = "/{lobbyId:[0-9]+}")
    public Optional<Lobby> getLobby(@PathVariable int lobbyId) {
        return lobbyLogic.getLobby(lobbyId);
    }

    @DeleteMapping(path = "/{lobbyId:[0-9]+}")
    public String deleteLobby(@PathVariable int lobbyId) {
        return lobbyLogic.deleteLobby(lobbyId);
    }

    /* Format:
     {
	     "userId":"INSERT_USERID"
     } */
    @PutMapping(path = "/{lobbyId:[0-9]+}")
    public Lobby updateLobbyOnUser(@PathVariable("lobbyId") int lobbyId, @RequestBody UserLobbyRequestModel userLobbyRequestModel) {
        return lobbyLogic.updateLobbyOnUser(lobbyId, userLobbyRequestModel.userId);
    }

    /* Format:
     {
	     "userId":"INSERT_USERID",
	     "lobbyId":"INSERT_LOBBYID"
     } */
    @DeleteMapping(path = "")
    public Lobby removeUserFromLobby(@RequestBody UserLobbyRequestModel userLobbyRequestModel) {
        return lobbyLogic.removeUserFromLobby(userLobbyRequestModel);
    }
}