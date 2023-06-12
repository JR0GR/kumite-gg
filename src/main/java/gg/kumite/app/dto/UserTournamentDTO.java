package gg.kumite.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTournamentDTO implements Serializable {
    private Long user_id;
    private Long tournament_id;
}
