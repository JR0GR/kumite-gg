package gg.kumite.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.kumite.app.dto.TournamentCreateDTO;
import gg.kumite.app.dto.TournamentDTO;
import gg.kumite.app.models.Game;
import gg.kumite.app.models.Tournament;
import gg.kumite.app.models.User;
import gg.kumite.app.repositories.GameRepository;
import gg.kumite.app.repositories.TournamentRepository;
import gg.kumite.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping(path = "/tournaments")
public class TournamentController {
   @Autowired
   TournamentRepository tournamentRepository;
   @Autowired
   UserRepository       userRepository;
   @Autowired
   GameRepository       gameRepository;

   @GetMapping("/")
   public ResponseEntity<Object> index() {
      List<TournamentDTO> resultado = new ArrayList<>();
      for (Tournament tournament : tournamentRepository.findAll()) {
         resultado.add(new TournamentDTO(tournament));
      }
      return new ResponseEntity<>(resultado, HttpStatus.OK);
   }

   @GetMapping("/{id}/")
   public ResponseEntity<Object> show(@PathVariable("id") Long id) {
      Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
      return new ResponseEntity<>(new TournamentDTO(tournament), HttpStatus.OK);
   }

   @PostMapping("/create/")
   public ResponseEntity<Object> create(@RequestBody TournamentCreateDTO tournamentCreateDTO) {
      Game game = gameRepository.findById(tournamentCreateDTO.getGameId()).orElseThrow(() -> new EntityNotFoundException(tournamentCreateDTO.getGameId().toString()));
      User user = userRepository.findById(tournamentCreateDTO.getCreatorId()).orElseThrow(() -> new EntityNotFoundException(tournamentCreateDTO.getCreatorId().toString()));
      Tournament tournament = tournamentRepository.save(new Tournament(tournamentCreateDTO, game, user));
      return new ResponseEntity<>(new TournamentDTO(tournament), HttpStatus.OK);
   }

   @DeleteMapping("/{id}/")
   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
   public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
      Optional<Tournament> tournament = tournamentRepository.findById(id);
      tournament.ifPresent(value -> tournamentRepository.delete(value));
      return new ResponseEntity<>(tournament.isPresent(), tournament.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
   }

   @PutMapping("/{id}/")
   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
   public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Tournament tournament) {
      Optional<Tournament> oldTournament = tournamentRepository.findById(id);
      if (oldTournament.isPresent()) {
         tournament.setId(id);
         tournamentRepository.save(tournament);
         return new ResponseEntity<>(new TournamentDTO(tournament), HttpStatus.OK);
      }
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
   }
}
