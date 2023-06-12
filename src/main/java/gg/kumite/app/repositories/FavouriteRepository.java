package gg.kumite.app.repositories;

import gg.kumite.app.models.Favourite;
import gg.kumite.app.models.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface FavouriteRepository extends CrudRepository<Favourite, Long> {
}
