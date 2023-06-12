package gg.kumite.app.repositories;

import gg.kumite.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
