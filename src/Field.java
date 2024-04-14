public class Field {
    private int ID;
    private String label;
    private int cost;
    private int income;
    private String option;

    public String getOption() {
        return option;
    }

    public Field(int id, String label, int cost, int income) {
        this.ID = id;
        this.label = label;
        this.cost = cost;
        this.income = income;
    }

    public String processResponse(boolean input, Player p) {//change to boolean argument
        String msg = "";
        if (input) {//change to boolean eval
            msg = onAccept(p);
        } else {//change to boolean eval
            msg = onReject(p);
        }

        return msg;
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
        return p.getName()+" accepterede \n ";
    }

    protected String onReject(Player p) {
        return p.getName()+" afviste \n";
    }

    public String getLabel() {
        return label;
    }

    public int getIncome() {
        return income;
    }
    public int getCost(){
        return this.cost;
    }

    protected void setOption(String option) {
        this.option = option;
    }

    protected void setIncome(int income) {
        this.income= income;
    }

    protected void setCost(int cost) {
        this.cost = cost;
    }
}