package gg.kumite.app.models;

import gg.kumite.app.dto.ProfileCreateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name = "profilecustom")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String phone;
    private boolean admin;
    private boolean moderator;
    private boolean active;
    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile(String name, String email, String password, String phone, boolean admin, boolean moderator, boolean active) {
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.phone = phone;
        this.admin = admin;
        this.moderator = moderator;
        this.active = active;
    }

    public Profile(ProfileCreateDTO profile) {
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.password = new BCryptPasswordEncoder().encode(profile.getPassword());
        this.phone = profile.getPhone();
        this.admin = profile.isAdmin();
        this.moderator = profile.isModerator();
        this.active = profile.isActive();
    }
}
