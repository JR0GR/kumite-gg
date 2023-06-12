package gg.kumite.app.models;

import gg.kumite.app.dto.TournamentCreateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "platforms_tournament", joinColumns = @JoinColumn(name ="id"))
    private List<String> platforms = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Game game;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User creator;
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<UserTournament> participants = new HashSet<>();
    private Boolean approved;
    private Boolean finished;

    public Tournament(String name, List<String> platforms, Game game, User creator) {
        this.name = name;
        this.platforms.addAll(platforms);
        this.game = game;
        this.creator = creator;
        this.approved = false;
        this.finished = false;
    }

    public Tournament(TournamentCreateDTO tournamentCreateDTO, Game game, User creator) {
        this.name = tournamentCreateDTO.getName();
        this.platforms.addAll(tournamentCreateDTO.getPlatforms());
        this.game = game;
        this.creator = creator;
        this.approved = false;
        this.finished = false;
    }
}
