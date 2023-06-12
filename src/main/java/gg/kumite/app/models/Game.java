package gg.kumite.app.models;

import gg.kumite.app.dto.GameCreateDTO;
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
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer release_year;
    private String imageId;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "platforms_game", joinColumns = @JoinColumn(name ="id"))
    private List<String> platforms = new ArrayList<>();
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<Tournament> tournaments = new HashSet<>();
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<Favourite> favourites = new HashSet<>();

    public Game(String name, Integer release_year, String imageId, List<String> platforms) {
        this.name = name;
        this.release_year = release_year;
        this.imageId = imageId;
        this.platforms.addAll(platforms);
    }

    public Game(GameCreateDTO gameCreateDTO) {
        this.name = gameCreateDTO.getName();
        this.release_year = gameCreateDTO.getYear();
        this.imageId = gameCreateDTO.getImageId();
        this.platforms.addAll(gameCreateDTO.getPlatforms());
    }
}
