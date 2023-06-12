package gg.kumite.app.dto;

import gg.kumite.app.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDTO implements Serializable {
    private Long id;
    private String name;
    private List<String> platforms;
    private Long gameId;
    private Long creatorId;
    private List<Long> participants = new ArrayList<>();
    private Boolean approved;
    private Boolean finished;
    private String imageId;

    public TournamentDTO(@NotNull Tournament tournament) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.platforms = tournament.getPlatforms();
        this.gameId = tournament.getGame().getId();
        this.creatorId = tournament.getCreator().getId();
        for(UserTournament participant: tournament.getParticipants())
            this.participants.add(participant.getUser().getId());
        this.approved = tournament.getApproved();
        this.finished = tournament.getFinished();
        this.imageId = tournament.getImageId();
    }
}
