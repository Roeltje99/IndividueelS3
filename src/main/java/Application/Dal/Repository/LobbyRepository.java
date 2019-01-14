package Application.Dal.Repository;

import Application.Dal.Entity.Lobby;
import org.springframework.data.repository.CrudRepository;

public interface LobbyRepository extends CrudRepository<Lobby, Integer> { }