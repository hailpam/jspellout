
package it.pm.jspellout.rules.handler;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;

/**
 * Manages the numbers of billions order of magnitude.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public abstract class BillionsHandler implements RulesHandler
{
    // successor in the processing pipeline
    private MillionsHandler pipeSuccessor;
    // successor to be used in case of composite billions
    private HundredsHandler compositeHandler;
    // number of digits expected - from
    private static final int NR_DIGITS_FROM = 10;
    // number of digits expected - to
    private static final int NR_DIGITS_TO = 12;
    
    
    public void manageDigits(int[] digits, Queue<SpellResult> results) throws SpellOutException 
    {
        // sanity checks on Inputs
        if(digits.length == 0)
            throw new SpellOutException("Millions Handler expects from "+NR_DIGITS_FROM+" to "+NR_DIGITS_TO+" "
                    + "Digits each one comprised in [0,9].");
        if(!(digits.length >= NR_DIGITS_FROM && digits.length <= NR_DIGITS_TO))
            throw new SpellOutException("Millions Handler expects only from "+NR_DIGITS_FROM+" to almost "+
                    NR_DIGITS_TO+" Digits.");
        for(int i = 0; i < digits.length; i++) {
            if(!(digits[i] >= 0 && digits[i]<=9))
                throw new SpellOutException("Millions Handler received digit ["+(i + 1)+" of "+digits.length+"]  "
                        + " which is Out of Range Value.");
        }
        if(results == null)
            throw new SpellOutException("Results Queue must be not NULL to push"
                    + " the results on this stage.");
        System.out.println("[Pipeline][BillionsHandler]");
    }

    public MillionsHandler getPipeSuccessor() 
    {
        return pipeSuccessor;
    }

    public void setPipeSuccessor(MillionsHandler pipeSuccessor) 
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
