import java.util.Random;

public class Dice {

    public boolean isDouble;
    Random rand = new Random();
    int dice1 = rand.nextInt(1,6);
    int dice2 = rand.nextInt(1,6);



    public int rollDiceSum(){
        int sum = dice1 + dice2;
        return sum;


    }

    public boolean isDouble(){
        return dice1 == dice2;

    }



}
