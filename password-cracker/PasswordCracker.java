// Name: Joe Spaniac
// 
// PasswordCracker guesses all possible password permutations 
// containing NUM_CHARS characters from CHARS
//
// Note: This application can take a good while to finish excecuting
//       with difficult passwords 

import java.util.*;
import java.util.function.Consumer;

public class PasswordCracker {
    // Characters chosen to create each "password"
    public static final String CHARS = "1234567890" /* nums */
                                    //  + "abcdefghijklmnopqrstuvwxyz" /* alpha */
                                    //  + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" /* caps */
                                    //  + "!@#$%^&*()-_=+`~[]{}\|;:'\",.<>/?" /* special */
                                     ; 
    // How many characters the "password" contains            
    public static final int NUM_CHARS = 4;

    // Main is arguably a bad summary of our program for this one :(
    public static void main(String[] args) {
        time(PasswordCracker::allCombos, NUM_CHARS);
    }

    // Given a function 'func' and parameter 'param', times how long
    // it takes for said function to run, printing a nicely formatted output
    public static void time(Consumer<Integer> func, int param) {
        long start = System.currentTimeMillis();
        func.accept(param);
        long end = System.currentTimeMillis();
        
        double secs = (end - start) / 1000.0;
        
        System.out.println();
        printTimeFormatted(secs);
    }

    // Given a total number of seconds 'totalTime', prints the total time in
    // the form "1:20.05s"
    private static void printTimeFormatted(double totalTime) {
        int mins = (int) totalTime / 60;
        double secs = totalTime % 60;
        secs = ((int) (secs * 100)) / 100;
        System.out.println("Total time - " + mins + ":" +
                                            ((secs < 10) ? "0" : "") +
                                            secs + "s");
    }

    // Given a certain number of characters 'numChars', prints out strings
    // of all permutations of the characters found in the CHARS constant
    public static void allCombos(int numChars) {
        allCombos(numChars, "");
    }

    // Given a certain number of characters remaining 'numCharsLeft', 
    // prints out strings of all permutations of 'soFar' plus the characters
    // found in the CHARS constant
    private static void allCombos(int numCharsLeft, String soFar) {
        if (numCharsLeft == 0) {
            System.out.println(soFar);
        } else {
            for (int i = 0; i < CHARS.length(); i++) {
                allCombos(numCharsLeft - 1, soFar + CHARS.charAt(i));
            }
        }
    }
}
