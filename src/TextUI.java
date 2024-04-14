import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {

    private Scanner scan = new Scanner(System.in);

    public void displayList(ArrayList<String> list, String msg){
        System.out.println(msg);
        for (String option : list) {
            System.out.println(option);
        }
    }
    public String promptText(String msg){
        displayMsg(msg);
        String input = scan.nextLine();
        return input;
    }
    public boolean promptBinary(String msg, String accept, String reject ){
        boolean output;
        // todo: check at der tastes enten y eller n
        // lav rekursivt kald hvis det er noget tredje

        String input = promptText(msg);
        if(input.equalsIgnoreCase(accept)){
            return true;
        }else if(input.equalsIgnoreCase(reject)){
            return false;
        }else{
            System.out.println("OBS! Tast enten '" + accept + "' eller '" + reject+"'");
            return promptBinary(msg,accept, reject);
        }

    }
    public int promptNumeric(String msg){
        String input = promptText(msg);         //Give brugere et sted at placere sit svar og vente pÃ¥ svaret
        int number = Integer.parseInt(input);       //Konvertere svaret til et tal
        return number;
    }
    public int promptChoice(ArrayList<String> optionslist, String msg){//["Gin&Tonic", "Beer","Vine" ]
        displayMsg(msg);
        displayList(optionslist, "");
        int input = promptNumeric("");//1
      //  return optionslist.get(input-1);
        return input;

    }

  public void displayMsg(String msg){
        if(msg!=null) {
            System.out.println("\n***************");
            System.out.println(msg);
            System.out.println("***************\n");
        }
  }

}