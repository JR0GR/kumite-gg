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
public class UserDTO implements Serializable {
    private Long id;
    private String nickname;
    private List<String> platforms;
    private String nationality;
    private String pictureId;
    private Long profileId;
    private List<Long> created = new ArrayList<>();
    private List<Long> tournaments = new ArrayList<>();
    private List<Long> favourites = new ArrayList<>();
    private Integer wins;

    public UserDTO(@NotNull User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.platforms = user.getPlatforms();
        this.nationality = user.getNationality();
        this.pictureId = user.getPictureId();
        this.profileId = user.getProfile().getId();
        for(Tournament tournament: user.getCreated())
            this.created.add(tournament.getId());
        for(UserTournament tournament: user.getTournaments())
            this.tournaments.add(tournament.getTournament().getId());
        for(Favourite favourite: user.getFavourites())
            this.favourites.add(favourite.getGame().getId());
        this.wins = user.getWins();
    }
}
