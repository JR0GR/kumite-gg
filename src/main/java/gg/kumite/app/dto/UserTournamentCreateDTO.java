package gg.kumite.app.dto;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import gg.kumite.app.models.UserTournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTournamentCreateDTO implements Serializable {
   private Long userId;
   private Long tournamentId;

   public UserTournamentCreateDTO(@NotNull UserTournament userTournament) {
      this.userId = userTournament.getUser().getId();
      this.tournamentId = userTournament.getTournament().getId();
   }
}
