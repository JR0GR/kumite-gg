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
public class FavouriteDTO implements Serializable {
   private Long user_id;
   private Long game_id;

   public FavouriteDTO(@NotNull Favourite favourite) {
      this.game_id = favourite.getGame().getId();
      this.user_id = favourite.getUser().getId();
   }
}
