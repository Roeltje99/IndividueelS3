import Application.Dal.Entity.Lobby;
import Application.Dal.Repository.LobbyRepository;
import Application.Logic.LobbyLogic;
import Application.Wrappers.UserLobbyRequestModel;
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
public class LobbyLogicTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    LobbyRepository lobbyRepository;

    @InjectMocks
    private LobbyLogic lobbyLogic;

    @Before
    public void SetUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateLobbyValid()
    {
        Lobby dummyLobby = new Lobby("test", null);
        Lobby dummyLobbyName = new Lobby("testLobby",null);

        when(lobbyRepository.save(any(Lobby.class))).thenReturn(dummyLobby);

        Lobby createdLobby = lobbyLogic.addLobby(dummyLobbyName);

        Assert.assertNotNull(createdLobby);
        verify(lobbyRepository, times(1)).save(any(Lobby.class));
    }

    @Test
    public void testCreateGameInvalid(){
        Lobby dummyLobbyName = new Lobby("",null);

        exception.expect(IllegalArgumentException.class);

        lobbyLogic.addLobby(dummyLobbyName);
    }

    @Test
    public void testGetAllLobbies()
    {
        Iterable<Lobby> lobbies = new LinkedList<Lobby>();
        ((LinkedList<Lobby>) lobbies).add(new Lobby("test", null));
        ((LinkedList<Lobby>) lobbies).add(new Lobby("testLobby",null));

        when(lobbyRepository.findAll()).thenReturn(lobbies);

        Iterable<Lobby> createdLobbies = lobbyLogic.getAllLobbies();

        Assert.assertNotNull(createdLobbies);
    }

    @Test
    public void testGetLobbyValid()
    {
        Lobby dummyLobby = new Lobby("test", null);
        dummyLobby.setLobbyId(1);
        Optional<Lobby> testLobby = Optional.of(dummyLobby);

        when(lobbyRepository.findById(1)).thenReturn(testLobby);

        Lobby createdLobby = lobbyLogic.getLobby(1).get();

        Assert.assertEquals(createdLobby.getLobbyId(), testLobby.get().getLobbyId());
    }

    @Test
    public void testGetLobbyInvalid()
    {
        exception.expect(IllegalArgumentException.class);

        Optional<Lobby> lobby = lobbyLogic.getLobby(2);
    }

    @Test
    public void testDeleteLobbyValid()
    {
        Lobby dummyLobby = new Lobby("test", null);
        dummyLobby.setLobbyId(1);
        Optional<Lobby> testLobby = Optional.of(dummyLobby);

        when(lobbyRepository.findById(1)).thenReturn(testLobby);

        String message = lobbyLogic.deleteLobby(1);

        Assert.assertEquals("Succesfully deleted", message);
    }

    @Test
    public void testDeleteLobbyInvalid()
    {
        exception.expect(IllegalArgumentException.class);

        String message = lobbyLogic.deleteLobby(2);
    }

    @Test
    public void testUpdateLobbyOnUserInvalidLobby()
    {
        exception.expect(IllegalArgumentException.class);

        lobbyLogic.updateLobbyOnUser(3, 2);
    }

    @Test
    public void testRemoveLobbyOnUserInvalidLobby()
    {
        exception.expect(IllegalArgumentException.class);
        UserLobbyRequestModel requestModel = new UserLobbyRequestModel();
        requestModel.lobbyId = 4;
        requestModel.userId = 12;
        lobbyLogic.removeUserFromLobby(requestModel);
    }
}