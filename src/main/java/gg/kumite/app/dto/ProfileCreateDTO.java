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
public class ProfileCreateDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private String phone;
    private boolean admin;
    private boolean moderator;
    private boolean active;
}
