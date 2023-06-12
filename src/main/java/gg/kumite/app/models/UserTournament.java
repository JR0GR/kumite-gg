package gg.kumite.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor
public class UserTournament {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Tournament tournament;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User user;
    private Integer position;

    public UserTournament(Tournament tournament, User user) {
        this.tournament = tournament;
        this.user = user;
        this.position = 0;
    }
}
