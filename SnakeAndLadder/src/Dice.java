import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    // diceCount = no of dices
    int diceCount;
    int min = 1;
    int max = 6;
    public Dice(int diceCount){
        this.diceCount = diceCount;
    }
    public int rollDice(){
        // logic to roll dice and get a number
        int totalSum=0;
        int diceUsed=0;

        while(diceUsed<diceCount){

            totalSum += ThreadLocalRandom.current().nextInt(min,max+1);
            diceUsed++;
        }

        return totalSum;

    }
}
