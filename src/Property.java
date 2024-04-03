public class Property extends Field {

    private int serieID;

    public Property(int id, String label, int cost, int income, int serieID){
        super(id, label, cost, income);
        this.serieID = serieID;

    }

    @Override
    public String toString() {
        return super.toString() + serieID;
    }

    @Override
    public String onLand(Player p) {
        return super.onLand(p);
    }

    @Override
    protected String onAccept(Player p) {
        return "";
    }

    @Override
    protected String onReject(Player p) {
        return "";
    }
}
