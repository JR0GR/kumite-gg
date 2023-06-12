package gg.kumite.app.models;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "images")
@NoArgsConstructor
@Getter
@Setter
public class Image {
   @Id
   private String id;

   private String title;

   private Binary image;

   public Image(String title, Binary image) {
      this.title = title;
      this.image = image;
   }
}
