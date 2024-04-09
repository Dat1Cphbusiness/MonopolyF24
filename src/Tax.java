public class Tax extends Prison{
    public Tax(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    protected String onAccept(Player p) {

        p.pay(getCost());

        String s = "Du har betalt";

        return s;
    }

    @Override
    protected String onReject(Player p) {

        int saldo = p.getBalance();
        double toPay = saldo* 0.10;
        p.pay((int) toPay);
        String s = " Du har betalt 10%";
        return s;
    }


    @Override
    public String onLand(Player p) {
        String s = super.onLand(p);

        s += "\n Vil du betale fast beløb på " + this.getCost() + "kr? Tast j for Ja \n ( Ellers trækker vi et beløb der svare til 10% af dine aktiver)";
        return s;


    }
}
