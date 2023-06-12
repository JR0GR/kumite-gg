package gg.kumite.app.controllers;

import gg.kumite.app.dto.ProfileCreateDTO;
import gg.kumite.app.dto.ProfileDTO;
import gg.kumite.app.models.Profile;
import gg.kumite.app.repositories.ProfileRepository;
import gg.kumite.app.services.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    @Autowired
    private ProfileRepository profileRepository;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

    @PostMapping("/profile/create")
    public ResponseEntity<?> create(@RequestBody ProfileCreateDTO profile) {
        Profile newProfile = profileRepository.save(new Profile(profile));
        return new ResponseEntity<>(new ProfileDTO(newProfile), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Profile profile = profileRepository.findById(id)
                                           .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return new ResponseEntity<>(new ProfileDTO(profile), HttpStatus.OK);
    }
}