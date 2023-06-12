package gg.kumite.app.dto;

import gg.kumite.app.models.Favourite;
import gg.kumite.app.models.Game;
import gg.kumite.app.models.Tournament;
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
public class GameDTO implements Serializable {
    private Long id;
    private String name;
    private Integer year;
    private String imageId;
    private List<String> platforms = new ArrayList<>();
    private List<Long> tournaments = new ArrayList<>();
    private List<Long> favourites = new ArrayList<>();

    public GameDTO(@NotNull Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.year = game.getRelease_year();
        this.imageId = game.getImageId();
        this.platforms.addAll(game.getPlatforms());
        for(Tournament tournament: game.getTournaments())
            this.tournaments.add(tournament.getId());
        for(Favourite favourite: game.getFavourites())
            this.favourites.add(favourite.getUser().getId());
        this.name = game.getName();
    }
}
