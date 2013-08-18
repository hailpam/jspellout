package it.pm.jspellout;

import java.util.Scanner;

import it.pm.jspellout.interpreter.NumbersInterpreter;
import it.pm.jspellout.model.SpellOutException;
import java.math.BigInteger;

/**
 * Main App class.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 *
 */
public class App 
{
    
    public static void main( String[] args )
    { 
        
        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";
        String numberSpelled = "";
        long number = 0;
        
        System.out.println("Welcome to jspellout v1.0!");
        System.out.println("__________________________\n");
        
        System.out.println("Usage: type any number (up to billions) to request the spell out");
        System.out.println("=================================================================");
        System.out.println(" QUIT : Exit");
        
        System.out.println("\n\n\n");
        
        BigInteger numberInput = null;
        while(true) {
            System.out.println("\n+------------------------------------+");
            System.out.println("Please, insert the number to spell out \n>> ");
            userInput = inputScanner.nextLine();
            if(userInput.equalsIgnoreCase("quit"))
                break;
            try {
                numberInput = new BigInteger(userInput);
                number = numberInput.abs().longValue();
                numberSpelled = NumbersInterpreter.getInstance().englishSpellOut(number);
                System.out.println("+------------------------------------+");
                System.out.println("Spelling-->>["+numberSpelled.trim()+"]");
            }catch(SpellOutException soe) {
                System.out.println("\n________________________________________________________");
                System.err.println("Error: Occurred during the spell out process :: ["+soe+"]\n");
            }catch(Exception e) {
                System.out.println("\n_________________________________");
                System.err.println("Error: Only Integers are accepted\n");
            }
            
        }
        
    }
    
}
