package gg.kumite.app.models;

import gg.kumite.app.dto.FavouriteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
public class Favourite {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Game game;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User user;

    public Favourite(Game game, User user) {
        this.game = game;
        this.user = user;
    }
}
