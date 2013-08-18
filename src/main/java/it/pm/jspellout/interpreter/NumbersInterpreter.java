
package it.pm.jspellout.interpreter;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.EnglishRules;
import it.pm.jspellout.templates.EnglishTemplates;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Numbers Interpreter Class. It parses a number as an array of digits and
 * interprets the transformation commands provided by the rules manager. 
 * 
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class NumbersInterpreter 
{
    // pattern to split a number in its composing digits
    private static final String DIG_SPLIT_PATTERN = "";
    // singleton instance
    private static NumbersInterpreter instance;
    
    // english rules engine
    private EnglishRules enRules;
    // english template engine
    private EnglishTemplates enTemplating;
    
    private NumbersInterpreter() 
    {
        this.enRules = new EnglishRules();
        this.enTemplating = new EnglishTemplates();
    }
    
    public synchronized static NumbersInterpreter getInstance() {
        if(instance == null) 
            instance = new NumbersInterpreter();
        return instance;
    }
    
    /**
     * English spell out API.
     * 
     * @param number The number to be spelled out
     * @return A string containing the word translation
     * 
     * @throws SpellOutException 
     */
    public String englishSpellOut(long number) throws SpellOutException 
    {
        StringBuilder spelling = new StringBuilder("");
        int[] digits = new int[new Long(number).toString().length()];
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        // from number to its digits
        double dNumber = number;
        int idx = 0;
        do {
            digits[idx] = (int) (number % 10); // interesting digit
            number = number / 10;
            dNumber = dNumber / 10;  // zeros (digits) needed
            idx += 1;
        }while(dNumber >= 1.0);
        try {
            // spell out rules call
            results = this.enRules.applyRules(digits);
            // translates the spell results
            while(!results.isEmpty()) {
                EnglishSpellResult result = (EnglishSpellResult) results.poll();
                spelling.append(this.enTemplating.applyTemplate(result));
            }
        }catch(SpellOutException soe) {
            System.err.println("Error occurred during the processing ["+soe.getMessage()+"]");
            throw new SpellOutException("Unable to process the spelling: "
                    + "Error: ["+soe.getMessage()+"]");
        }
        
        return spelling.toString();
    }
    
    /**
     * Italian spell out API.
     * 
     * @param number The number to be spelled out
     * @return A string containing the word translation
     * 
     * @throws SpellOutException 
     */
    public String italianSpellOut(long number) throws SpellOutException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * French spell out API.
     * 
     * @param number The number to be spelled out
     * @return A string containing the word translation
     * 
     * @throws SpellOutException 
     */
    public String frenchSpellOut(long number) throws SpellOutException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * German spell out API.
     * 
     * @param number The number to be spelled out
     * @return A string containing the word translation
     * 
     * @throws SpellOutException 
     */
    public String germanSpellOut(long number) throws SpellOutException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
