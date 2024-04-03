import java.util.ArrayList;

public class CardDeck{
    private String[] carddata;
    private ArrayList<Card> cards;
    public int counter = 0;
    public CardDeck(String[] carddata){
        this.carddata = carddata;



    }

    public void createCards(String[] carddata){
        for (int i = 0; i < carddata.length; i++) {
            String[] values = carddata[i].split(",");
            String message = values[0];
            int income = Integer.parseInt(values[1]);
            int cost = Integer.parseInt(values[2]);
            String event = values[3];
            Card c = new Card(message, income, cost, event);
            cards.add(c);

        }
    }
    public Card getNext(){
        counter++;
        if(counter > 100){
            counter = 0;
        }

        return cards.get(counter);
    }

}
