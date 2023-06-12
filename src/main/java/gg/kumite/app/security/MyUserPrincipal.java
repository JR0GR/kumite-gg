package gg.kumite.app.security;

import gg.kumite.app.models.Profile;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class MyUserPrincipal implements UserDetails {
    private Profile profile;

    public MyUserPrincipal(Profile profile) {
        this.profile = profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> resultado = new ArrayList<>();
        if(profile.isAdmin())
            resultado.add(new SimpleGrantedAuthority("ADMIN"));
        if(profile.isModerator())
            resultado.add(new SimpleGrantedAuthority("MOD"));
        return resultado;
    }

    @Override
    public String getPassword() {
        return profile.getPassword();
    }

    @Override
    public String getUsername() {
        return profile.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return profile.isActive();
    }
}