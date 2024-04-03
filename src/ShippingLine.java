public class ShippingLine extends Property{

    public ShippingLine(int id, String label, int cost, int income, int serieID) {
        super(id, label, cost, income, serieID);
    }

    @Override
    public String onLand(Player p) {
        return super.onLand(p);
    }
}
