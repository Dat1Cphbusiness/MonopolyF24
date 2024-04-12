import java.util.ArrayList;

public class  Player {
    private String name;
    private ArrayList<Field> deeds;
    private Account account;
    private int position;


    private int diceDoubleCount;
  /*  enum DoubleCount {
        1,
        2,
        3
    }*/
    public Player(String name, int balance, int position){
        this.name = name;
        deeds = new ArrayList<>();
        this.account = new Account(balance);
        this.position = position;
    }


    public String toString(){
        return this.name +", "+this.account.getBalance()+ ", "+ this.position;
    }

    public int getBalance() {
        return this.account.getBalance();
    }

    public String getName() {
        return name;
    }

    public int updatePosition(int value){

        this.position += value;
        if(position > 40){
            //todo pay borgerløn
            position-=40;
        }
        return position;
    }

    public boolean buyProperty(Field f){
        f.getCost();
        boolean success = this.account.withdraw(f.getCost());


        return success;

    }

    public boolean pay(int amount){
        boolean success = this.account.withdraw(amount);
        return success;

    }

    public void receive(int amount){
        this.account.deposit(amount);

    }
    // this pay is for payRent
    public boolean pay(int amount, Player recipient){
        recipient.receive(amount);
        return pay(amount);

    }

    public void collect(int amount){
      ArrayList<Player> listOfPlayers = Main.games.get(0).getPlayers();
        for (Player p : listOfPlayers){
            p.pay(amount, this);

        }

    }


    public boolean startPassed(){
        return true;
    }

    public int getPosition(){
        return this.position;
    }

    public int totalValue() {
        int sum = this.getBalance();
        for (Field d:deeds) {
            sum += d.getCost();
        }
        return sum;
    }

}
