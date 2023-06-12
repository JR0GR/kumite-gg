package gg.kumite.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateDTO implements Serializable {
    private String name;
    private Integer year;
    private String imageId;
    private List<String> platforms;
}
