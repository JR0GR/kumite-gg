package gg.kumite.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.kumite.app.dto.FavouriteCreateDTO;
import gg.kumite.app.models.Favourite;
import gg.kumite.app.models.Game;
import gg.kumite.app.models.User;
import gg.kumite.app.repositories.FavouriteRepository;
import gg.kumite.app.repositories.GameRepository;
import gg.kumite.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping(path = "/favourite")
public class FavoriteController {
   @Autowired
   FavouriteRepository favouriteRepository;

   @Autowired
   GameRepository      gameRepository;

   @Autowired
   UserRepository      userRepository;

   @PostMapping("/create/")
   public ResponseEntity<Object> create(@RequestBody FavouriteCreateDTO favouriteCreateDTO) {
      Game game = gameRepository.findById(favouriteCreateDTO.getGameId()).orElseThrow(() -> new EntityNotFoundException(favouriteCreateDTO.toString()));
      User user = userRepository.findById(favouriteCreateDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException(favouriteCreateDTO.toString()));
      Favourite favourite = favouriteRepository.save(new Favourite(game, user));
      return new ResponseEntity<>(new FavouriteCreateDTO(favourite), HttpStatus.OK);
   }

   @DeleteMapping("/delete/")
   public ResponseEntity<Object> delete(@RequestBody FavouriteCreateDTO favouriteCreateDTO) {
      Game game = gameRepository.findById(favouriteCreateDTO.getGameId()).orElseThrow(() -> new EntityNotFoundException(favouriteCreateDTO.toString()));
      User user = userRepository.findById(favouriteCreateDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException(favouriteCreateDTO.toString()));
      Optional<Favourite> favourite = favouriteRepository.findByGameAndUser(game, user);
      favourite.ifPresent(value -> favouriteRepository.delete(value));
      return new ResponseEntity<>(favourite.isPresent(), favourite.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
   }
}
