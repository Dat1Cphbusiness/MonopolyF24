public class Chance extends Prison{
    public Chance(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    public String onLand(Player p) {
        Card card = Game.cardDeck.getNext();
        String s = super.onLand(p);
        this.cost = card.getCost();
        this.income = card.getIncome();
        this.option = card.getEvent();
        s += "\n Træk et kort fra bunken: " + card.getMessage();
        
        return s;
    }
    @Override
    public String onAccept(Player p){
        String value = this.option;

        //TODO p.startPassed() og implementing af methode

        switch (value) {
            case "payment":
                p.pay(this.cost);
                break;
            case "reward":
                p.recieve(this.income);
                break;
            case "collect":
                p.collectFromAll(this.income);
                break;
            case "-3":
                p.updatePosition(-3);
                break;
            case "1":
                p.updatePosition(41 - p.getPosition());
                break;
            case "3":
                p.updatePosition(3);
                break;
            case "11":
                p.updatePosition((51 - p.getPosition()) % 40);
                break;
            case "25":
                p.updatePosition((65 - p.getPosition()) % 40);
                break;
            case "33":
                p.updatePosition((73 - p.getPosition()) % 40);
                break;
            case "38":
                p.updatePosition((78 - p.getPosition()) % 40);
                break;
            case "40":
                p.updatePosition(40 - p.getPosition());
                break;

        }
        return "";
    }
}
