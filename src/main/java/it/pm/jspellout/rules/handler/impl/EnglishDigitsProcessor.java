
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.rules.handler.DigitsProcessor;

/**
 * English specific Digits Handler.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishDigitsProcessor extends DigitsProcessor
{
    
    public EnglishDigitsProcessor() 
    {
        // set the instance with specific handlers
        super(new EnglishBillionsHandler(), new EnglishMillionsHandler(), new EnglishThousandsHandler(),
                new EnglishHundredsHandler(), new EnglishTensHandler(), new EnglishUnitsHandler());
    }
    
}
