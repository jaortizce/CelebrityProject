package learnCelebrity.finalProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == init ==
    @PostConstruct
    public void init() {
        log.info("The value of the celebrity prior to the game is {}", (game.getCelebrityNumber() + 1));
    }

    @Override
    public String getMainMessage() {
        return "The celebrity number after matrix building is " + game.validateCelebrity();
    }

}
