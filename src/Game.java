import java.util.ArrayList;

public class Game {
    private String name;
    private ArrayList<Player> players;
    private ArrayList<String> listOfActions;
    private TextUI ui;
    private FileIO io;
    private String playerDataPath = "data/playerData.csv";


    private int maxPlayers = 6;


    private Player currentPlayer;

    boolean morePlayers = true;

    public Game(String name) {
        this.name = name;

        this.ui = new TextUI();
        this.io = new FileIO();

        players = new ArrayList<>();

        listOfActions = new ArrayList<>();
        listOfActions.add("1) start new game");
        listOfActions.add("2) continue game");
        listOfActions.add("3) quit game");

    }

    public void createPlayer(String name, int balance) {

        if (name == null) {
            name = ui.promptText("Type player name: ");//eller 'q' for at afslutte
        }
      //  if (!name.equalsIgnoreCase("q")) {
            currentPlayer = new Player(name, balance);
            this.players.add(currentPlayer);
       /* }else if(this.players.size()>1){
            morePlayers = false;
        }else{
             ui.displayMsg("Der skal være mindst 2 spillere");
             createPlayer(null, 0);
     }*/



    }

    public void runDialog(){
        ui.displayMsg("welcome to "+this.name);
        int action = 0;

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
    private void loadPlayerData() {
        ArrayList<String> data = io.readPlayerData(playerDataPath);  //"Tess, 2000"
        // obs: hvis der allerede er startet et nyt spil , og vi så loader flere spillere,
        // vil vi både få spillere fra det nystartede spil og fra det gemte spil i player listen
        players = new ArrayList<>();//clear playerlisten.
        if(data.size()>0){
            for (String s:data) {
                String[] values = s.split(",");//"Tess, 2000" == ["Tess", " 2000"]
                int balance = Integer.parseInt(values[1].trim());
                 String name = values[0];
                 createPlayer(name, balance);
            }

        }else{
            registerPlayers();
        }
    }
    private void registerPlayers() {
        players = new ArrayList<>();//reset players array, so that a new game may be created mid session

        /*
        Bud1: Johan og Lasse
        int numberOfPlayers = ui.promptNumeric("Tast antal spillere:");
         while(this.players.size() < numberOfPlayers ){
            createPlayer(null,0);
        }
        Problem: ingen test af om numberOfPlayers er mellem 2 og 6
        */



      //  Bud2: André


        boolean morePlayers = true;
        while(morePlayers && players.size()<6){
            String name = ui.promptText("Tast spiller navn eller tast Q");
            if(!name.equalsIgnoreCase("Q")){
                createPlayer(name,0);
            }else{
                if(players.size() >1) {
                    morePlayers = false;
                }
                ui.displayMsg("Minimum 2 spillere");
            }

        }
    //    problem: UX, man skal hele tiden svare på om der skal flere spillere

/*
        while(this.players.size() < 6){
            createPlayer(null,0);
        }

*/

    }

    public void displayPlayers(){
    for(Player c: players){
        System.out.println(c);
    }
}
 private void runGameLoop(){
        int count = 0;
        String input = "Y";
        while(input.equalsIgnoreCase("Y")){
            currentPlayer = players.get(count);
            ui.displayMsg("Det er "+currentPlayer.getName()+"'s tur");
            //throwAndMove()
            input = ui.promptText("Fortsæt? Y/N: ");
            count++;
            if(count == players.size()){
                count = 0;
            }
        }
    }
}
