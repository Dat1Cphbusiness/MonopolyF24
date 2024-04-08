public class Field {
    private int ID;
    private String label;
    private int cost;
    private int income;
    private String option;

    public Field(int id, String label, int cost, int income) {
        this.ID = id;
        this.label = label;
        this.cost = cost;
        this.income = income;
    }

    @Override
    public String toString() {
        return "felt " + ID + ": " + label;
    }

    public String onLand(Player p) {
        String s = p.getName() + " er landet på " + this.toString();
        return s;
    }
    public String processResponse(Player p) {
        return "";
    }

    protected String onAccept(Player p) {
        return "";
    }

    protected String onReject(Player p) {
        return "";
    }

    public int getCost() {
        return cost;
    }
}