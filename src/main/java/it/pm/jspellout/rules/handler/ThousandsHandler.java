
package it.pm.jspellout.rules.handler;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;

/**
 * Manages numbers of thousands order of magnitude.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public abstract class ThousandsHandler implements RulesHandler
{
    // successor in the processing pipeline
    private HundredsHandler pipeSuccessor;
    // successor to be used in case of composite billions
    private HundredsHandler compositeHandler;
    // number of digits expected - from
    private static final int NR_DIGITS_FROM = 4;
    // number of digits expected - to
    private static final int NR_DIGITS_TO = 6;

    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        // sanity checks on Inputs
        if(digits.length == 0)
            throw new SpellOutException("Thousands Handler expects from "+NR_DIGITS_FROM+" to "+NR_DIGITS_TO+" "
                    + "Digits each one comprised in [0,9].");
        if(!(digits.length >= NR_DIGITS_FROM && digits.length <= NR_DIGITS_TO))
            throw new SpellOutException("Thousands Handler expects only from "+NR_DIGITS_FROM+" to almost "+
                    NR_DIGITS_TO+" Digits.");
        for(int i = 0; i < digits.length; i++) {
            if(!(digits[i] >= 0 && digits[i]<=9))
                throw new SpellOutException("Thousands Handler received digit ["+(i + 1)+" of "+digits.length+"]  "
                        + " which is Out of Range Value.");
        }
        if(results == null)
            throw new SpellOutException("Results Queue must be not NULL to push"
                    + " the results on this stage.");
        System.out.println("[Pipeline][ThousandsHandler]");
    }

    public HundredsHandler getPipeSuccessor() 
    {
        return pipeSuccessor;
    }

    public void setPipeSuccessor(HundredsHandler pipeSuccessor) 
    {
        this.pipeSuccessor = pipeSuccessor;
    }

    public HundredsHandler getCompositeHandler() 
    {
        return compositeHandler;
    }

    public void setCompositeHandler(HundredsHandler compositeHandler) 
    {
        this.compositeHandler = compositeHandler;
    }
    
}
