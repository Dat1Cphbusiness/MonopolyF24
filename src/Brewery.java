public class Brewery extends Property{

    public Brewery(int id, String label, int cost, int income, int serieID) {
        super(id, label, cost, income, serieID);
    }

    @Override
    public String onLand(Player p) {
        return super.onLand(p);
    }
}
