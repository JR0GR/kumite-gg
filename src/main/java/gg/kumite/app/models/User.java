package gg.kumite.app.models;

import gg.kumite.app.dto.UserCreateDTO;
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
@Table(name = "usercustom")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "platforms_user", joinColumns = @JoinColumn(name ="id"))
    private List<String> platforms = new ArrayList<>();
    // using https://restcountries.com/ in front-end
    private String nationality;
    // stores image's mongo id
    private String pictureId;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "profileid", referencedColumnName = "id")
    private Profile profile;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<Tournament> created = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserTournament> tournaments = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Favourite> favourites = new HashSet<>();
    private Integer wins;

    public User(String nickname, List<String> platforms, String nationality, String pictureId, Profile profile) {
        this.nickname = nickname;
        this.platforms.addAll(platforms);
        this.nationality = nationality;
        this.pictureId = pictureId;
        this.profile = profile;
        this.wins = 0;
    }

    public User(UserCreateDTO userCreateDTO, Profile profile) {
        this.nickname = userCreateDTO.getNickname();
        this.platforms.addAll(userCreateDTO.getPlatforms());
        this.nationality = userCreateDTO.getNationality();
        this.pictureId = userCreateDTO.getPictureId();
        this.profile = profile;
        this.wins = 0;
    }
}
