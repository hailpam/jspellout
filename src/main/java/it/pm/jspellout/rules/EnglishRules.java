
package it.pm.jspellout.rules;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.impl.EnglishDigitsProcessor;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * English Rules. Set of rules and logic to interpret and properly spell out
 * numbers in the english language.
 *  
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishRules implements SpellRules
{
    // english digits processor
    private EnglishDigitsProcessor digitsProc;
    
    public EnglishRules() 
    {
        // build an instance of english rules processor
        this.digitsProc = new EnglishDigitsProcessor();
    }
    
    public Queue<SpellResult> applyRules(int[] digits) throws SpellOutException 
    {
        // building the pipeline
        this.digitsProc.buildPipeline();
        // build a new spell results queue
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        // inputs sanity checks
        if(digits == null || digits.length == 0)
            throw new SpellOutException("Expected a significant Array of digits in Input.");
        // choicing the most appropriate call according to the order of magnitude
        if(digits.length >= 10 && digits.length <= 12) {
            System.out.println("[EnglishRules][applyRules] Order of magnitude in "
                    + "[10, 12] digits \n\t-->> Processing Billions");
            this.digitsProc.processBillions(digits, results);
        }else if(digits.length >= 7 && digits.length <= 9) {
            System.out.println("[EnglishRules][applyRules] Order of magnitude in "
                    + "[7, 9] digits \n\t-->> Processing Millions");
            this.digitsProc.processMillions(digits, results);
        }else if(digits.length >= 4 && digits.length <= 6) {
            System.out.println("[EnglishRules][applyRules] Order of magnitude in "
                    + "[4, 6] digits \n\t-->> Processing Thousands");
            this.digitsProc.processThousands(digits, results);
        }else if(digits.length == 3) {
            System.out.println("[EnglishRules][applyRules] Order of magnitude is "
                    + "3 digits \n\t-->> Processing Hundreds");
            this.digitsProc.processHundreds(digits, results);
        }else if(digits.length == 2) {
            System.out.println("[EnglishRules][applyRules] Order of magnitude is "
                    + "2 digits \n\t-->> Processing Tens");
            this.digitsProc.processTens(digits, results);
        }else if(digits.length == 1) {
            System.out.println("[EnglishRules][applyRules] Order of magnitude is "
                    + "1 digits \n\t-->> Processing Units");
            this.digitsProc.processUnits(digits, results);
        }else
            throw new SpellOutException("["+digits.length+"]digits numbers are not "
                    + "in the supported range.");
        
        return results;
    }
    
}
