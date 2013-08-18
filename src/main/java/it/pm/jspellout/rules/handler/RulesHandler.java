
package it.pm.jspellout.rules.handler;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;

/**
 * Defines the rules handler generic interface.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public interface RulesHandler 
{
    /**
     * Manages the digits provided in input according to the specific rules.
     * 
     * @param digits To be analyzed
     * @param results Spell out results to be pushed on Queue
     * @throws SpellOutException 
     */
    void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException;
}
