
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.HundredsHandler;
import java.util.Arrays;
import java.util.Queue;

/**
 * English hundreds specific handler.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishHundredsHandler extends HundredsHandler
{

    @Override
    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        super.manageDigits(digits, results);
        // manages its figure
        EnglishSpellResult result = new EnglishSpellResult(false, false);
        result.setFigure(digits[2]);
        result.setMagnitude(OrdersOfMagnitude.HUNDREDS);
        if(digits[2] > 0 && digits[1] == 0 && digits[0] == 0) {
            result.setSpecialCase(true);
            results.add(result);
        }else if(digits[2] == 0 && (digits[1] != 0 || digits[0] != 0)) { // in case there's something to process
            // manages the remaining figures
            int[] tens = Arrays.copyOfRange(digits, 0, 2);
            super.getPipeSuccessor().manageDigits(tens, results);
        }else if(digits[2] > 0 && (digits[1] != 0 || digits[0] != 0)) { // in case there's something to process
            // add it and manages the remaining figures
            results.add(result);
            int[] tens = Arrays.copyOfRange(digits, 0, 2);
            super.getPipeSuccessor().manageDigits(tens, results);
        }
    }
    
}
