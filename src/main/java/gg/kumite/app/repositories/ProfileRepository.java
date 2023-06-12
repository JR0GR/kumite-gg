package gg.kumite.app.repositories;

import gg.kumite.app.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByName(String name);
}