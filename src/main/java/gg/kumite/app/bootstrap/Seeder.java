package gg.kumite.app.bootstrap;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import gg.kumite.app.factories.GameFactory;
import gg.kumite.app.models.Favourite;
import gg.kumite.app.models.Game;
import gg.kumite.app.models.Image;
import gg.kumite.app.models.Profile;
import gg.kumite.app.models.Tournament;
import gg.kumite.app.models.User;
import gg.kumite.app.models.UserTournament;
import gg.kumite.app.repositories.FavouriteRepository;
import gg.kumite.app.repositories.GameRepository;
import gg.kumite.app.repositories.ImageRepository;
import gg.kumite.app.repositories.ProfileRepository;
import gg.kumite.app.repositories.TournamentRepository;
import gg.kumite.app.repositories.UserRepository;
import gg.kumite.app.repositories.UserTournamentRepository;


@Component
public class Seeder implements CommandLineRunner {
   @Autowired
   UserRepository           userRepository;
   @Autowired
   GameRepository           gameRepository;
   @Autowired
   ProfileRepository        profileRepository;
   @Autowired
   TournamentRepository     tournamentRepository;
   @Autowired
   FavouriteRepository      favouriteRepository;
   @Autowired
   UserTournamentRepository userTournamentRepository;
   @Autowired
   ImageRepository          imageRepository;

   @Autowired
   GameFactory              gameFactory;

   @Override
   public void run(String... args) throws IOException {
      if (profileRepository.count() == 0) {

         BufferedImage profileImageFile = ImageIO.read(getClass().getResource("/profileImage.png"));
         BufferedImage gameImage1File = ImageIO.read(getClass().getResource("/gameImage1.png"));
         BufferedImage gameImage2File = ImageIO.read(getClass().getResource("/gameImage2.png"));
         BufferedImage gameImage3File = ImageIO.read(getClass().getResource("/gameImage3.png"));
         BufferedImage tournamentImage1File = ImageIO.read(getClass().getResource("/tournamentImage1.png"));
         BufferedImage tournamentImage2File = ImageIO.read(getClass().getResource("/tournamentImage2.png"));
         BufferedImage tournamentImage3File = ImageIO.read(getClass().getResource("/tournamentImage3.png"));
         Image profileImage = imageRepository.save(new Image("profileImage.png", binarizeImage(profileImageFile)));
         Image gameImage1 = imageRepository.save(new Image("gameImage1.png", binarizeImage(gameImage1File)));
         Image gameImage2 = imageRepository.save(new Image("gameImage2.png", binarizeImage(gameImage2File)));
         Image gameImage3 = imageRepository.save(new Image("gameImage3.png", binarizeImage(gameImage3File)));
         Image tournamentImage1 = imageRepository.save(new Image("tournamentImage1.png", binarizeImage(tournamentImage1File)));
         Image tournamentImage2 = imageRepository.save(new Image("tournamentImage2.png", binarizeImage(tournamentImage2File)));
         Image tournamentImage3 = imageRepository.save(new Image("tournamentImage3.png", binarizeImage(tournamentImage3File)));

         Game game1 = gameRepository.save(new Game("Mortal Kombat 11", 2019, gameImage1.getId(), Arrays.asList("PC", "XBOX", "PS5", "Switch")));
         Game game2 = gameRepository.save(new Game("Guilty Gear Strive", 2021, gameImage2.getId(), Arrays.asList("PC", "XBOX", "PS5", "Switch")));
         Game game3 = gameRepository.save(new Game("Dragon Ball FighterZ", 2018, gameImage3.getId(), Arrays.asList("PC", "XBOX", "PS5", "Switch")));

         Profile testProfile = profileRepository.save(new Profile("jr", "jr@gmail.com", "jr", "", true, false, true));
         Profile testProfile2 = profileRepository.save(new Profile("jrg", "jrg@gmail.com", "jrg", "", false, true, true));

         User user1 = userRepository.save(new User("bob", game1.getPlatforms(), "ES", profileImage.getId(), testProfile));
         User user2 = userRepository.save(new User("jane", game1.getPlatforms(), "ES", profileImage.getId(), testProfile2));

         favouriteRepository.save(new Favourite(game1, user1));
         favouriteRepository.save(new Favourite(game2, user1));
         favouriteRepository.save(new Favourite(game2, user2));
         favouriteRepository.save(new Favourite(game3, user2));
         Tournament t1 = tournamentRepository.save(new Tournament("campeonato 1", game1.getPlatforms(), game1, user1, tournamentImage1.getId()));
         Tournament t2 = tournamentRepository.save(new Tournament("campeonato 2", game2.getPlatforms(), game2, user2, tournamentImage2.getId()));
         Tournament t3 = tournamentRepository.save(new Tournament("campeonato 3", game3.getPlatforms(), game3, user1, tournamentImage3.getId()));
         userTournamentRepository.save(new UserTournament(t1, user1));
         userTournamentRepository.save(new UserTournament(t2, user1));
         userTournamentRepository.save(new UserTournament(t3, user1));
         userTournamentRepository.save(new UserTournament(t1, user2));
         userTournamentRepository.save(new UserTournament(t2, user2));
         userTournamentRepository.save(new UserTournament(t3, user2));

      }
   }

   private static Binary binarizeImage(BufferedImage img_param) {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      try {
         ImageIO.write(img_param, "png", bytes);
      }
      catch (IOException e) {
      }
      byte[] resultantimage = Base64Utils.encode(bytes.toByteArray());

      return new Binary(resultantimage);
   }
}
