package gg.kumite.app.security;

import gg.kumite.app.models.Profile;
import gg.kumite.app.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        Profile profile = profileRepository.findByName(name);
        if (profile == null) {
            throw new UsernameNotFoundException(name);
        }
        return new MyUserPrincipal(profile);
    }
}