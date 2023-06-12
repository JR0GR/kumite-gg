package gg.kumite.app.bootstrap;

import gg.kumite.app.factories.GameFactory;
import gg.kumite.app.models.*;

import gg.kumite.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    FavouriteRepository favouriteRepository;
    @Autowired
    UserTournamentRepository userTournamentRepository;

    @Autowired
    GameFactory gameFactory;

    @Override
    public void run(String... args) {
        if(profileRepository.count() == 0) {
            Profile testProfile = profileRepository.save(new Profile("jr", "jr@gmail.com", "jr", "", true, false, true));
            Profile testProfile2 = profileRepository.save(new Profile("jrg", "jrg@gmail.com", "jrg", "", false, true, true));
            List<Game> games = gameFactory.get(5);
            gameRepository.saveAll(games);

            User user1 = userRepository.save(new User("bob", games.get(0).getPlatforms(), "ES", "", testProfile));
            User user2 = userRepository.save(new User("jane", games.get(0).getPlatforms(), "ES", "", testProfile2));

            favouriteRepository.save(new Favourite(games.get(0), user1));
            favouriteRepository.save(new Favourite(games.get(1), user1));
            favouriteRepository.save(new Favourite(games.get(1), user2));
            favouriteRepository.save(new Favourite(games.get(2), user2));
            Tournament t1 = tournamentRepository.save(new Tournament("campeonato 1", games.get(0).getPlatforms(), games.get(0), user1));
            Tournament t2 = tournamentRepository.save(new Tournament("campeonato 2", games.get(1).getPlatforms(), games.get(1), user2));
            Tournament t3 = tournamentRepository.save(new Tournament("campeonato 3", games.get(2).getPlatforms(), games.get(2), user1));
            userTournamentRepository.save(new UserTournament(t1, user1));
            userTournamentRepository.save(new UserTournament(t2, user1));
            userTournamentRepository.save(new UserTournament(t3, user1));
            userTournamentRepository.save(new UserTournament(t1, user2));
            userTournamentRepository.save(new UserTournament(t2, user2));
            userTournamentRepository.save(new UserTournament(t3, user2));

        }
    }
}
