package gg.kumite.app.factories;

import com.github.javafaker.Faker;
import gg.kumite.app.models.Game;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component

public class GameFactory {
    Faker esFaker = new Faker(new Locale("es-ES"));

    List<String> platforms = Arrays.asList("PC", "XBOX", "PS5", "Switch");
    public Game getGame() {
        return new Game(esFaker.esports().game(),
                esFaker.number().numberBetween(2005, 2023),
                "",
                platforms);
    }

    public List<Game> get(int number) {
        return IntStream.range(0, number)
                .mapToObj(x -> this.getGame())
                .collect(Collectors.toList());
    }
}
