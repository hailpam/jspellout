
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.BillionsHandler;
import java.util.Arrays;
import java.util.Queue;

/**
 * English Billions specific handler.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishBillionsHandler extends BillionsHandler
{

    @Override
    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        super.manageDigits(digits, results);
        // handle the composition
        int[] billions = new int[3];
        int idx = 0, number = 0;
        do {
            billions[idx] = digits[idx + 9];
            number += (billions[idx]*(Math.pow(10, idx)));
            idx += 1;
            if(idx + 10 > digits.length)
                break;
        }while(idx < 3);
        super.getCompositeHandler().manageDigits(billions, results);
        // push in queue the composite thousands
        if(number > 0) {
            EnglishSpellResult result = new EnglishSpellResult(false, true);
            result.setFigure(number);
            result.setMagnitude(OrdersOfMagnitude.BILLIONS);
            results.add(result);
        }
        // handle the remaining part of digits in pipeline
        int[] millions = Arrays.copyOfRange(digits, 0, 9);
        super.getPipeSuccessor().manageDigits(millions, results);
    }
    
}
