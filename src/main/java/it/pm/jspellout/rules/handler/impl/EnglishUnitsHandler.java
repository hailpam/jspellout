
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import it.pm.jspellout.rules.handler.UnitsHandler;
import java.util.Queue;

/**
 * English specific Units handler.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishUnitsHandler extends UnitsHandler
{

    @Override
    public void manageDigits(int[] digits, Queue<SpellResult> results) 
            throws SpellOutException 
    {
        super.manageDigits(digits, results); 
        // create the result, nothingelse to check
        EnglishSpellResult result = new EnglishSpellResult(false, false);
        result.setFigure(digits[0]);
        result.setMagnitude(OrdersOfMagnitude.UNITS);
        // push results on the queue
        results.add(result);
    }
    
}
