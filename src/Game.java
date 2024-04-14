import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet{
    private final int id;
    private PApplet pApplet;
    private String name;
    private static boolean processFlag = true;
    private ArrayList<String> listOfActions;
    private TextUI ui;
    private FileIO io;
    private String playerDataPath = "data/playerData.csv";
    private String fieldDataPath = "data/fieldData.csv";
    private String cardDataPath = "data/cardData.csv";
    private Dice dice = new Dice();
    private int maxPlayers = 6;

    private Player currentPlayer;
    protected CardDeck cardDeck;
    protected ArrayList<Player> players;

    private Board board;
    static int count = 0;


    public Game(String name) {

        this.name = name;

        this.ui = new TextUI();
        this.io = new FileIO();

        players = new ArrayList<>();

        listOfActions = new ArrayList<>();
        listOfActions.add("1) start new game");
        listOfActions.add("2) continue game");
        listOfActions.add("3) quit game");
        this.boardSetup();
        this.id = count;
        count++;
    }

    /**
    *
    * Contructor overloading. Used when GUI is present
    * Alternatively, subclass Game with GraphicalGame and TextBasedGame
    *
    * */
    public Game(String name, PApplet pApplet){

        this(name);
        this.pApplet = pApplet;
    }

    public void setProcessFlag(boolean b) {
        processFlag = b;
    }


    private void boardSetup() {
         String [] fieldData = io.readBoardData(fieldDataPath,40);
         this.board = new Board(fieldData);
         String [] cardData = io.readBoardData(cardDataPath,46);
         this.cardDeck = new CardDeck(cardData);



    }

    public void createPlayer(String name, int balance, int position) {
            currentPlayer = new Player(name, balance, position);
            this.players.add(currentPlayer);
    }

    public void runDialog(){

        ui.displayMsg("Welcome to "+this.name);
        int action=0;
        while(action != listOfActions.size()){// the quit action is the last action
          action = ui.promptChoice(listOfActions, "Choose action:");

          switch(action){
             case 1:
                  //start new game
                  this.registerPlayers();
                  this.runGameLoop();
                  break;
              case 2:
                  //Continue (last saved) game
                  this.loadPlayerData();
                  this.displayPlayers();
                  this.runGameLoop();
                  break;
             case 3:
                 //quit game
                 this.endGame();
                 break;

         }

        }
    }


    private void endGame() {
        io.saveData(this.players, playerDataPath);
    }
    public void loadPlayerData() {
        ArrayList<String> data = io.readPlayerData(playerDataPath);  //"Tess, 2000"
        // obs: hvis der allerede er startet et nyt spil , og vi så loader flere spillere,
        // vil vi både få spillere fra det nystartede spil og fra det gemte spil i player listen
        players = new ArrayList<>();//clear playerlisten.
        if(data.size()>0){
            for (String s:data) {
                String[] values = s.split(",");//"Tess, 2000" >> ["Tess", " 2000"]
                int balance = Integer.parseInt(values[1].trim());
                 String name = values[0];
                 int position = Integer.parseInt(values[2].trim());
                 createPlayer(name, balance, position);
            }

        }else{
            registerPlayers();
        }
    }
    private void registerPlayers() {
        players = new ArrayList<>();//reset players array, so that a new game may be created mid session
        boolean morePlayers = true;
        while(morePlayers && players.size() < maxPlayers){
            String name = ui.promptText("Tast spiller navn. 'Q' for at quitte");
            if(!name.equalsIgnoreCase("Q")){
                createPlayer(name,0, 1);

            }else{
                if(players.size() > 1) {
                    morePlayers = false;
                }
                ui.displayMsg("Minimum 2 spillere");
            }

        }


    }

    public void displayPlayers(){
    for(Player c: players){
        System.out.println(c);
    }
}
 private void runGameLoop(){
        int count = 0;
        boolean input = true;
        while(input){

            // todo: var det et dobbelslag?
            // todo: some kind of counter
            // if currentPlayer diceDoubleCount  > 0  && <3 , count--

            currentPlayer = players.get(count);
            // if currentPlayer diceDoubleCount == 3, i fængsel

            ui.displayMsg("Det er "+currentPlayer.getName()+"'s tur");
            setProcessFlag(true);
            throwAndMove();
            input = ui.promptBinary("Fortsæt? Y/N: ", "Y", "N");
            count++;
            if(count == players.size()){
                count = 0;
            }
        }
    }

    public void throwAndMove(){

            int result = dice.rollDiceSum();

            ui.displayMsg(currentPlayer.getName()+" slog "+dice.getDice()[0] +" og "+dice.getDice()[1]);
            int newPosition = currentPlayer.updatePosition(result);
            Field f = board.getField(3);

            landAndAct(f);
    }

     public void landAndAct(Field f){

         String msg = f.onLand(currentPlayer);
         boolean response = true;
         if(processFlag) {
            response = ui.promptBinary(msg, "Y", "N");
         }else{
             ui.displayMsg(msg);
         }
         msg = f.processResponse(response, currentPlayer);
         ui.displayMsg(msg);
         ui.displayMsg(currentPlayer.toString());
     }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public CardDeck getDeck() {
        return this.cardDeck;
    }

    public int getID() {
        return id;
    }

    public Board getBoard() {
        return this.board;
    }
    public void setup(){
        String[] appletArgs = new String[]{"Game"};
        PApplet.main(appletArgs);
    }
    public void settings() {
        size(760, 900);
    }
}
