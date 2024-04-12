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
        System.out.println(msg);
        String input = scan.nextLine();
        return input;
    }
    public String promptBinary(String msg, String option1, String option2 ){
        String input = promptText(msg);

        if((input.equalsIgnoreCase(option1)  || input.equalsIgnoreCase(option2))){

        } else{
            displayMsg("ugyldig svar, prøv igen");
            promptBinary(msg, option1, option2);
        }
        return input;
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

      System.out.println(msg);
  }

}