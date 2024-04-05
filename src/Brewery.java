public class Brewery extends Business{
    public Brewery(int id, String label, int cost, int income, int serieID) {
        super(id, label, cost, income, serieID);
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
