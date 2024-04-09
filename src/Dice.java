import java.util.Random;

public class Dice {

    private boolean isDouble;
    Random rand = new Random();


    private int dice1;
    private int dice2;


    int[]diceSum = new int[2];




    public int rollDiceSum(){
        dice1 = rand.nextInt(6)+1;
        dice2 = rand.nextInt(6)+1;
        int sum = dice1 + dice2;

        if(dice1 == dice2){
           isDouble = true;
        }else{
            isDouble = false;
        }
        return sum;


    }

    public boolean isDouble(){
        return isDouble;

    }

    public int[] getDice(){

            diceSum[0] = dice1;
            diceSum[1] = dice2;


        return diceSum;



    }


}
