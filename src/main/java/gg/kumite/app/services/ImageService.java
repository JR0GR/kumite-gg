package gg.kumite.app.services;

import java.io.IOException;
import java.util.Base64;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.kumite.app.models.Image;
import gg.kumite.app.repositories.ImageRepository;


@Service
public class ImageService {
   @Autowired
   private ImageRepository imageRepo;

   public String addImage(String title, String base64String) throws IOException {
      Image image = new Image();
      image.setTitle(title);
      image.setImage(new Binary(BsonBinarySubType.BINARY, Base64.getDecoder().decode(base64String)));
      image = imageRepo.insert(image);
      return image.getId();
   }

   public Image getImage(String id) {
      return imageRepo.findById(id).get();
   }
}
