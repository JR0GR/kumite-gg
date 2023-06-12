package gg.kumite.app.dto;

import gg.kumite.app.models.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean admin;
    private boolean moderator;
    private boolean active;
    private Long userId;

    public ProfileDTO(@NotNull Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.phone = profile.getPhone();
        this.admin = profile.isAdmin();
        this.moderator = profile.isModerator();
        this.active = profile.isActive();
        this.userId = profile.getUser().getId();
    }
}
