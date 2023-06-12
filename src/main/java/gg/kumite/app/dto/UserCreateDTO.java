package gg.kumite.app.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO implements Serializable {
    private String nickname;
    private List<String> platforms;
    private String nationality;
    private String pictureId;
    private Long profileId;
}
