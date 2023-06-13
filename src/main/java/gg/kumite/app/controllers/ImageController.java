package gg.kumite.app.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gg.kumite.app.dto.ImageCreateDTO;
import gg.kumite.app.models.Image;
import gg.kumite.app.services.ImageService;


@RestController
public class ImageController {
   @Autowired
   ImageService imageService;

   @PostMapping("/images/add")
   public String addPhoto(@RequestBody ImageCreateDTO imageCreateDTO) throws IOException {
      return imageService.addImage(imageCreateDTO.getTitle(), imageCreateDTO.getBase64());
   }

   @GetMapping("/images/{id}")
   public String getPhoto(@PathVariable String id) {
      Image image = imageService.getImage(id);
      return Base64.getEncoder().encodeToString(image.getImage().getData());
   }
}
