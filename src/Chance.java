import java.util.ArrayList;

public class Chance extends Field{
    public Chance(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    public String onLand(Player p) {

        CardDeck deck = Main.getCurrentGame().getDeck();
        Card card = deck.getNext();
        String s = super.onLand(p);
        this.setCost(card.getCost());
        this.setIncome(card.getIncome());
        this.setOption(card.getEvent());
        s += "\n Træk et kort fra bunken: " + card.getMessage();
        Main.getCurrentGame().setProcessFlag(false);
        return s;
    }
    @Override
    public String onAccept(Player p){

        String msg = "";
                try {
                    int steps = Math.abs(p.getPosition() - Integer.parseInt(this.getOption()));

                    p.updatePosition(steps);
                }catch(NumberFormatException e){
                    switch (this.getOption()) {
                        case "payment":
                            p.pay(this.getCost());
                            msg = "Du har betalt.";
                            break;
                        case "reward":
                            p.receive(this.getIncome());
                            msg = "Du har modtaget penge.";
                            break;
                        case "collect":
                            p.collect(this.getIncome());
                            msg = "Du har modtaget penge fra alle.";
                            break;
                        case "rederi":
                            p.updatePosition((16 - p.getPosition() % 10) % 10);
                            break;
                        case "joker":
                           // p.addCard();
                    }
                }


        return msg;
    }
}
