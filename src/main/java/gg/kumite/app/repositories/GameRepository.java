package gg.kumite.app.repositories;

import gg.kumite.app.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
