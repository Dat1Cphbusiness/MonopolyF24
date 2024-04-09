public class Tax extends Field{
    public Tax(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    public String onLand(Player p) {
        String msg = super.onLand(p);
        msg += "Skat trækker et beløb svarende til 10% af dine aktiver.\n"
            +  "Vil du hellere betale et fast beløb på " + this.getCost() + "kr? Y/N";
        return msg;
    }
    @Override
    public String onAccept(Player p) {
        p.pay(this.getCost());
        return "Du har betalt din SKAT <3 :-*";
    }

    @Override
    public String onReject(Player p) {
        int toPay = p.getTotalValue()/10;
        p.pay(toPay);
        return "Du har betalt 10% SKAT, svarende til " + toPay + "kr.";
    }
}
