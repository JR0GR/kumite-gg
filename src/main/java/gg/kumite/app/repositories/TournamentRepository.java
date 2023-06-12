package gg.kumite.app.repositories;

import gg.kumite.app.models.Tournament;
import gg.kumite.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {
}
