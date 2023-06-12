package gg.kumite.app.controllers;

import gg.kumite.app.dto.GameCreateDTO;
import gg.kumite.app.dto.GameDTO;
import gg.kumite.app.models.Game;
import gg.kumite.app.repositories.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/games")
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/")
    public ResponseEntity<Object> index() {
        List<GameDTO> resultado = new ArrayList<>();
        for (Game game: gameRepository.findAll()) {
            resultado.add(new GameDTO(game));
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        Game game = gameRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return new ResponseEntity<>(new GameDTO(game), HttpStatus.OK);
    }

    @PostMapping("/create/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> create(@RequestBody GameCreateDTO gameCreateDTO) {
        Game game = gameRepository.save(new Game(gameCreateDTO));
        return new ResponseEntity<>(new GameDTO(game), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Game> game = gameRepository.findById(id);
        game.ifPresent(value -> gameRepository.delete(value));
        return new ResponseEntity<>(game.isPresent(), game.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody GameCreateDTO gameCreateDTO) {
        Optional<Game> oldGame = gameRepository.findById(id);
        if(oldGame.isPresent()) {
            Game game = new Game(gameCreateDTO);
            game.setId(id);
            gameRepository.save(game);
            return new ResponseEntity<>(new GameDTO(game), HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
