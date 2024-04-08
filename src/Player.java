import java.util.ArrayList;

public class  Player {
    private String name;
    private ArrayList<Field> deeds;
    private Account account;
    private int position;

    public Player(String name, int balance) {
        this.name = name;

        deeds = new ArrayList<>();


        this.account = new Account(balance);
        this.setBalance(balance);
    }

    private void setBalance(int balance) {
        this.account.deposit(balance);
    }

    public String toString() {
        return this.name + ", " + this.account.getBalance();
    }

    public int getBalance() {
        return this.account.getBalance();
    }

    public String getName() {
        return name;
    }

    public int updatePosition(int value) {
        this.position += value;
        return position;
    }


    public boolean buyProperty(Field f) {

        boolean buySucced = account.withdraw(f.getCost());

        return buySucced;
    }

    public boolean pay(int amount) {
        boolean succedPay = account.withdraw(amount);
        return succedPay;
    }

    public void receive(int amount) {

        account.deposit(amount);
    }

    public boolean pay(int amount, Player recipient) {
        boolean payReceived = account.withdraw(amount);
        recipient.receive(amount);
        {
            return payReceived;
        }

    }

    public void collect(int amount){

        for (Player p : Game.getPlayers()) {

            p.pay(amount, this);

        }
    }
}
