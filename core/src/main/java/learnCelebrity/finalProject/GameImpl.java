package learnCelebrity.finalProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Stack;

@Component
public class GameImpl implements Game {

    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // == fields ==

    private final NumberGenerator numberGenerator;
    private int[][] matrix = new int[1000][1000];

    @Autowired
    public GameImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    private int celebrityNumber;
    private int biggest;

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        biggest = numberGenerator.getMaxNumber();
        celebrityNumber = numberGenerator.next();
        this.matrix = numberGenerator.getMatrix(biggest,celebrityNumber);
        log.debug("The number of people is {}", biggest);
        log.debug("The number of the celebrity is {}", celebrityNumber + 1);
        printMatrix(this.matrix);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
    }

    @Override
    public void printMatrix(int[][] mat) {
        for (int i = 0; i < biggest; i++){
            for (int j = 0; j < biggest; j++){
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    @Override
    public int getCelebrityNumber() {
        return celebrityNumber;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }


    // True if firstNumber knows secondNumber
    public boolean knows(int firstNumber, int secondNumber) {
        boolean res = (matrix[firstNumber][secondNumber] == 1) ? true : false;
        return res;
    }

    // Returns -1 if celebrity
    // is not present. If present,
    // returns id (value from 0 to n-1).
    public int findCelebrity(int n) {
        Stack<Integer> peopleStack = new Stack<>();
        int celebrity;

        // Step 1 :Push everybody onto stack
        for (int i = 0; i < n; i++) {
            peopleStack.push(i);
        }

        while (peopleStack.size() > 1) {
            // Step 2 :Pop off top two persons from the stack, discard one person based on return status of knows(firstNumber, secondNumber).
            int firstNumber = peopleStack.pop();
            int secondNumber = peopleStack.pop();

            // Step 3 : Push the remained person onto stack.
            if (knows(firstNumber, secondNumber)) {
                peopleStack.push(secondNumber);
            } else
                peopleStack.push(firstNumber);
        }

        celebrity = peopleStack.pop();

        // Step 5 : Validate if the last person is celebrity or not
        for (int i = 0; i < n; i++) {

            if (i != celebrity && (knows(celebrity, i) || !knows(i, celebrity))) {
                return -1;
            }
        }
        return celebrity;
    }

    public String validateCelebrity() {

        int result = findCelebrity(this.biggest);
        if (result == -1) {   //always -1 because if 1 is the response from findCelebrity the case of 1 will not be found, and indeed it could be found
            return "No Celebrity found";
        } else {
            return "Celebrity ID " + (result + 1);
        }
    }


}
