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
public class UserTournamentDTO implements Serializable {
   private Long user_id;
   private Long tournament_id;

   public UserTournamentDTO(@NotNull UserTournament userTournament) {
      this.user_id = userTournament.getUser().getId();
      this.tournament_id = userTournament.getTournament().getId();
   }
}
