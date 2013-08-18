
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.MillionsHandler;
import java.util.Arrays;
import java.util.Queue;

/**
 * English specific millions handler.
 * 
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishMillionsHandler extends MillionsHandler
{

    @Override
    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        super.manageDigits(digits, results); 
        // handle the composition
        int[] millions = new int[3];
        int idx = 0, number = 0;
        do {
            millions[idx] = digits[idx + 6];
            number += (millions[idx]*(Math.pow(10, idx)));
            idx += 1;
            if(idx + 7 > digits.length)
                break;
        }while(idx < 3);
        super.getCompositeHandler().manageDigits(millions, results);
        // push in queue the composite thousands
        if(number > 0) {
            EnglishSpellResult result = new EnglishSpellResult(false, true);
            result.setFigure(number);
            result.setMagnitude(OrdersOfMagnitude.MILLIONS);
            results.add(result);
        }
        // handle the remaining part of digits in pipeline
        int[] thousands = Arrays.copyOfRange(digits, 0, 6);
        super.getPipeSuccessor().manageDigits(thousands, results);
    }
    
}
