package utils;
import java.util.Scanner;


public class Keyboard {
    private static Scanner kb = new Scanner(System.in);

    public static String inputString(String args){
        System.out.print(args);
        return kb.nextLine();
    }


    public static Integer inputInt(String args){
          System.out.print(args);
          int res = kb.nextInt();
          kb.nextLine(); 
          return res;
    }
}




