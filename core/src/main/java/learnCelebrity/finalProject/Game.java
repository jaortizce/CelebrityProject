package learnCelebrity.finalProject;

public interface Game {

    int getCelebrityNumber();

    int getBiggest();

    void reset();

    boolean knows(int a, int b);

    int findCelebrity(int n);

    String validateCelebrity();

    void printMatrix(int mat [][]);

}
