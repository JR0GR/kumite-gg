package gg.kumite.app.dto;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import gg.kumite.app.models.Favourite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteCreateDTO implements Serializable {
   private Long userId;
   private Long gameId;

   public FavouriteCreateDTO(@NotNull Favourite favourite) {
      this.gameId = favourite.getGame().getId();
      this.userId = favourite.getUser().getId();
   }
}
