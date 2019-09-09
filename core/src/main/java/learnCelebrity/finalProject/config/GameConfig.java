package learnCelebrity.finalProject.config;

import learnCelebrity.finalProject.GuessCount;
import learnCelebrity.finalProject.MaxNumber;
import learnCelebrity.finalProject.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "learnCelebrity.finalProject")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber:20}")
    private int maxNumber;

    @Value("${game.minNumber:2}")
    private int minNumber;

    @Value("${game.guessCount:5}")
    private int guessCount;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }
}
