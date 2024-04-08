import java.util.ArrayList;

public class Game {

    private String name;
    private static ArrayList<Player> players;
    private ArrayList<String> listOfActions;
    private TextUI ui;
    private FileIO io;
    private String playerDataPath = "data/playerData.csv";
    private String fieldDataPath = "data/fieldData.csv";
    private String cardDataPath = "data/cardData.csv";
    private Dice dice = new Dice();
    private int maxPlayers = 6;


    private Player currentPlayer;


    private Board board;
    private CardDeck cardDeck;

    public Game(String name) {
        this.name = name;

        this.ui = new TextUI();
        this.io = new FileIO();

        players = new ArrayList<>();

        listOfActions = new ArrayList<>();
        listOfActions.add("1) start new game");
        listOfActions.add("2) continue game");
        listOfActions.add("3) quit game");
        this.setup();
    }

    private void setup() {
        String[] fieldData = io.readBoardData(fieldDataPath, 40);
        this.board = new Board(fieldData);
        System.out.println(board.getField(40));

        String[] cardData = io.readBoardData(cardDataPath, 46);
        this.cardDeck = new CardDeck(cardData);
    }


    public void createPlayer(String name, int balance) {
        currentPlayer = new Player(name, balance);
        this.players.add(currentPlayer);
    }

    public void runDialog() {
        ui.displayMsg("welcome to " + this.name);

        int action = 0;
        while (action != listOfActions.size()) {// the quit action is the last action
            action = ui.promptChoice(listOfActions, "Choose action:");

            switch (action) {
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
        if (data.size() > 0) {
            for (String s : data) {
                String[] values = s.split(",");//"Tess, 2000" >> ["Tess", " 2000"]
                int balance = Integer.parseInt(values[1].trim());
                String name = values[0];
                createPlayer(name, balance);
            }

        } else {
            registerPlayers();
        }
    }

    private void registerPlayers() {
        players = new ArrayList<>();//reset players array, so that a new game may be created mid session
        boolean morePlayers = true;
        while (morePlayers && players.size() < maxPlayers) {
            String name = ui.promptText("Tast spiller navn. 'Q' for at quitte");
            if (!name.equalsIgnoreCase("Q")) {
                createPlayer(name, 0);

            } else {
                if (players.size() > 1) {
                    morePlayers = false;
                }
                ui.displayMsg("Minimum 2 spillere");
            }

        }


    }

    public void displayPlayers() {
        for (Player c : players) {
            System.out.println(c);
        }
    }

    private void runGameLoop() {
        int count = 0;
        String input = "Y";
        while (input.equalsIgnoreCase("Y")) {

            // todo: var det et dobbelslag?
            //todo: some kind of counter
            currentPlayer = players.get(count);
            ui.displayMsg("Det er " + currentPlayer.getName() + "'s tur");
            throwAndMove();
            input = ui.promptText("Fortsæt? Y/N: ");
            count++;
            if (count == players.size()) {
                count = 0;
            }
        }
    }

    public void throwAndMove() {
        int result = dice.rollDiceSum();

        int newPosition = currentPlayer.updatePosition(result);
        Field f = board.getField(newPosition);

        //  System.out.println(f);

        f.landAndAct();

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

}
