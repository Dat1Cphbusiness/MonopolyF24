 public class Tax extends Field{
    public Tax(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    protected String onAccept(Player p) {
        String msg = "Du har betalt "+this.getCost()+" kr. i Skat.";
        p.pay(this.getCost());
        return msg;
    }

    @Override
    protected String onReject(Player p) {
        int tax = (int) (p.getBalance() * 0.1);
        p.pay(tax);
        String msg = "Du har betalt " + tax + "kr. i Skat.";
        return msg;
        // todo: Når deeds og houses er lavet kan man ændre i metoden så den siger (getBalance()+deeds+houses) inden den gange med 0.1
    }

@Override
    public String onLand(Player p) {
    String msg = super.onLand(p);
    msg += "Skat trækker et beløb der svarer til 10% af dine aktiver. \n Vil du hellere betale et fast beløb på " + this.getCost() + " kr.? Y/N";
        return msg;
    }
}
