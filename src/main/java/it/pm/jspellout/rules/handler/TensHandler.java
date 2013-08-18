
package it.pm.jspellout.rules.handler;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;

/**
 * Manages numbers of tens order of magnitude.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public abstract class TensHandler implements RulesHandler
{
    // successor in the processing pipeline
    private UnitsHandler pipeSuccessor;
    // number of digits expected
    private static final int NR_DIGITS = 2;

    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        // sanity checks on Inputs
        if(digits.length == 0)
            throw new SpellOutException("Tens Handler expects "+NR_DIGITS+" Digits each one comprised in [0,9].");
        if(digits.length != NR_DIGITS)
            throw new SpellOutException("Tens Handler expects only "+NR_DIGITS+" Digits.");
        for(int i = 0; i < NR_DIGITS; i++) {
            if(!(digits[i] >= 0 && digits[i]<=9))
                throw new SpellOutException("Tens Handler received digit ["+(i + 1)+" of "+NR_DIGITS+"]  "
                        + " which is Out of Range Value.");
        }
        if(results == null)
            throw new SpellOutException("Results Queue must be not NULL to push"
                    + " the results on this stage.");
        System.out.println("[Pipeline][TensHandler]");
    }

    public UnitsHandler getPipeSuccessor() 
    {
        return pipeSuccessor;
    }

    public void setPipeSuccessor(UnitsHandler pipeSuccessor) 
    {
        this.pipeSuccessor = pipeSuccessor;
    }
    
}
