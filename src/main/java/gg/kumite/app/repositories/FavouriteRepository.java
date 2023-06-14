package gg.kumite.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gg.kumite.app.models.Favourite;
import gg.kumite.app.models.Game;
import gg.kumite.app.models.User;


public interface FavouriteRepository extends CrudRepository<Favourite, Long> {
   Optional<Favourite> findByGameAndUser(Game game, User user);
}
