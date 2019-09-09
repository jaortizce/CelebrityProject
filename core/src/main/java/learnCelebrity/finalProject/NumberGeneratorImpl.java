package learnCelebrity.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final Random random = new Random();

    private final int maxNumber;

    // == constructors ==

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber) {
        this.maxNumber = maxNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(maxNumber);

    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }


    @Override
    public int[][] getMatrix(int numberOfPeople, int celebrityIndex) {

        int [][] matrixBuild = new int [numberOfPeople][numberOfPeople];

        for (int i = 0; i < numberOfPeople; i++){
            for (int j = 0; j < numberOfPeople; j++){
                if (j != celebrityIndex){
                    matrixBuild[i][j] = 0;
                }else if (j == celebrityIndex){
                    if (i != celebrityIndex){
                        matrixBuild[i][j] = 1;
                    }else if (i == celebrityIndex){
                        matrixBuild[i][j] = 0;
                    }
                }
            }
        }
        return matrixBuild;
    }


}
