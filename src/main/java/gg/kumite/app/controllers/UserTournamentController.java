package gg.kumite.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.kumite.app.dto.UserTournamentCreateDTO;
import gg.kumite.app.models.Tournament;
import gg.kumite.app.models.User;
import gg.kumite.app.models.UserTournament;
import gg.kumite.app.repositories.TournamentRepository;
import gg.kumite.app.repositories.UserRepository;
import gg.kumite.app.repositories.UserTournamentRepository;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping(path = "/userTournament")
public class UserTournamentController {
   @Autowired
   TournamentRepository     tournamentRepository;
   @Autowired
   UserRepository           userRepository;
   @Autowired
   UserTournamentRepository userTournamentRepository;

   @PostMapping("/create/")
   public ResponseEntity<Object> create(@RequestBody UserTournamentCreateDTO userTournamentCreateDTO) {
      Tournament tournament = tournamentRepository.findById(userTournamentCreateDTO.getTournamentId()).orElseThrow(() -> new EntityNotFoundException(userTournamentCreateDTO.toString()));
      User user = userRepository.findById(userTournamentCreateDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException(userTournamentCreateDTO.toString()));
      UserTournament userTournament = userTournamentRepository.save(new UserTournament(tournament, user));
      return new ResponseEntity<>(new UserTournamentCreateDTO(userTournament), HttpStatus.OK);
   }

}
