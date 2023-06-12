package gg.kumite.app.controllers;

import gg.kumite.app.dto.UserCreateDTO;
import gg.kumite.app.dto.UserDTO;
import gg.kumite.app.models.Profile;
import gg.kumite.app.models.User;
import gg.kumite.app.repositories.ProfileRepository;
import gg.kumite.app.repositories.UserRepository;
import gg.kumite.app.security.MyUserPrincipal;
import gg.kumite.app.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(
                StreamSupport.stream(userRepository.findAll().spliterator(), false)
                        .map(UserDTO::new),
                HttpStatus.OK);
    }

    @GetMapping("/me/")
    public ResponseEntity<Object> showMe() {
        String myUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepository.findByName(myUser);
        User user = profile.getUser();
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return new ResponseEntity<>(user.isPresent()? new UserDTO(user.get()) : false,
                                    user.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> create(@RequestBody UserCreateDTO user) {
        Profile profile = profileRepository.findById(user.getProfileId())
                                           .orElseThrow(() -> new EntityNotFoundException(user.getProfileId().toString()));
        return new ResponseEntity<>(
                new UserDTO(userRepository.save(new User(user, profile))),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        userRepository.delete(user);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody UserCreateDTO userCreateDTO) {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()) {
            Profile profile = profileRepository.findById(userCreateDTO.getProfileId())
                                               .orElseThrow(() -> new EntityNotFoundException(userCreateDTO.getProfileId().toString()));
            User user = new User(userCreateDTO, profile);
            user.setId(id);
            userRepository.save(user);
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
