
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.ThousandsHandler;
import java.util.Arrays;
import java.util.Queue;

/**
 * English specific thousands handler.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishThousandsHandler extends ThousandsHandler
{

    @Override
    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        super.manageDigits(digits, results);
        // handle the composition
        int[] thousands = new int[3];
        int idx = 0, number = 0;
        do {
            thousands[idx] = digits[idx + 3];
            number += (thousands[idx]*(Math.pow(10, idx)));
            idx += 1;
            if(idx + 4 > digits.length)
                break;
        }while(idx < 3);
        super.getCompositeHandler().manageDigits(thousands, results);
        // push in queue the composite thousands
        if(number > 0) {
            EnglishSpellResult result = new EnglishSpellResult(false, true);
            result.setFigure(number);
            result.setMagnitude(OrdersOfMagnitude.THOUSANDS);
            results.add(result);
        }
        // handle the remaining part of digits in pipeline
        int[] hundreds = Arrays.copyOfRange(digits, 0, 3);
        super.getPipeSuccessor().manageDigits(hundreds, results);
    }
    
}
