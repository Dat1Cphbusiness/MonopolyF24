import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class CardDeck{
    private int counter = 0;
    private FileIO io;
    private String cardDataPath = "data/cardData.csv";

    private ArrayList<Card> cards;
    public CardDeck(String[] carddata){

        this.cards = new ArrayList<Card>();
    }

    public void createCards(String[] carddata){
        for (int i = 0; i < carddata.length; i++) {
            String[] values = carddata[i].split(",");
            String message = values[0];
            int income = Integer.parseInt(values[1]);
            int cost = Integer.parseInt(values[2]);
            String event = values[3];
            Card c = new Card(message, income, cost, event);

        }
    }
    public Card getNext(){

//        if(counter > cards.size()){
//            counter = 0;
//            Collections.shuffle(cards);
//        }
//        Card card = cards.get(counter);
//        counter ++;
//        return card;

        // anden måde
        if(counter < 0){
            counter = cards.size();
            String [] cardData = io.readBoardData(cardDataPath, cards.size());
        }
        Random r = new Random();
        int randomCard = r.nextInt(counter);
        counter--;
        Card card = cards.get(randomCard);
        cards.remove(randomCard);
        return card;
    }
}
