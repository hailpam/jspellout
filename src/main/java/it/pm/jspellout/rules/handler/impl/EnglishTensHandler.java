
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.TensHandler;
import java.util.Arrays;
import java.util.Queue;

/**
 * English specific tens handler.
 *  
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishTensHandler extends TensHandler
{

    @Override
    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        super.manageDigits(digits, results); 
        int number = digits[0] + (10 * digits[1]);
        // special case
        EnglishSpellResult result = new EnglishSpellResult(false, false);
        result.setMagnitude(OrdersOfMagnitude.TENS);
        if(number >= 10 && number <= 20 || number == 30 || number == 40 || number == 50
                || number == 60 || number == 70 || number == 80 || number == 90) {
            result.setFigure(number);
            result.setSpecialCase(true);
            results.add(result);
        }else {
            // in case it's zero, nothing there's to be taken into account
            if(digits[1] > 0) {
                result.setFigure(digits[1]);
                results.add(result);
            }
            // call the successor
            int[] units = Arrays.copyOfRange(digits, 0, 1);
            super.getPipeSuccessor().manageDigits(units, results);
        }   
    }
    
}
