public class ShippingLine extends Business{
    public ShippingLine(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    protected String onAccept(Player p) {
        return super.onAccept(p);
    }

    @Override
    protected String onReject(Player p) {
        return super.onReject(p);
    }
}
