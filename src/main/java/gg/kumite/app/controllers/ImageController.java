package gg.kumite.app.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gg.kumite.app.models.Image;
import gg.kumite.app.services.ImageService;


@RestController
public class ImageController {
   @Autowired
   ImageService imageService;

   @PostMapping("/images/add")
   public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image) throws IOException {
      return imageService.addImage(title, image);
   }

   @GetMapping("/images/{id}")
   public String getPhoto(@PathVariable String id) {
      Image image = imageService.getImage(id);
      return "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getImage().getData());
   }
}
