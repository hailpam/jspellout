
package it.pm.jspellout.rules;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;

/**
 * Interface that defines the standard behaviour of rules engine components.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public interface SpellRules 
{
    
    /**
     * According to the input digits, it applies the most appropriate rules
     * to spell out the number.
     * 
     * @param digits An array of digits representing the number
     * @return A queue containing the spell result to be used to build the final
     *          string
     * 
     * @throws SpellOutException 
     */
    Queue<SpellResult> applyRules(int[] digits) throws SpellOutException;
    
}
