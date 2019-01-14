package application.Dal.Repository;

import application.Dal.Entity.Lobby;
import org.springframework.data.repository.CrudRepository;

public interface LobbyRepository extends CrudRepository<Lobby, Integer> { }