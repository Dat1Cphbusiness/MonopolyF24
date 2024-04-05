import org.w3c.dom.ls.LSOutput;

import java.util.Random;

public class Dice {

    public boolean isDouble;

    public int rollDiceSum() {
        Random rand = new Random();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        isDouble = die1 == die2;
        return die1 + die2;
    }
}
