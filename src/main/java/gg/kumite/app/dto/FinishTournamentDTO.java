package gg.kumite.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinishTournamentDTO implements Serializable {
   private Long userId;
   private Long TournamentId;
}
