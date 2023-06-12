package gg.kumite.app.dto;

import gg.kumite.app.models.Tournament;
import gg.kumite.app.models.UserTournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentCreateDTO implements Serializable {
    private String name;
    private List<String> platforms;
    private Long gameId;
    private Long creatorId;
    private String imageId;
}
