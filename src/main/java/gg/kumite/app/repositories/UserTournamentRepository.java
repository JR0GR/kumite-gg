package gg.kumite.app.repositories;

import gg.kumite.app.models.Profile;
import gg.kumite.app.models.UserTournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTournamentRepository extends JpaRepository<UserTournament, Long> {
}