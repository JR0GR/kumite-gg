package gg.kumite.app.services;

import gg.kumite.app.models.Image;
import gg.kumite.app.repositories.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;

    public String addImage(String title, MultipartFile file) throws IOException {
        Image image = new Image(title);
        image.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = imageRepo.insert(image);
        return image.getId();
    }

    public Image getImage(String id) {
        return imageRepo.findById(id).get();
    }
}
