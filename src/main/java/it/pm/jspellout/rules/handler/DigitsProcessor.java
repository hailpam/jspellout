
package it.pm.jspellout.rules.handler;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;

/**
 * Manages the processing pipeline. 
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public abstract class DigitsProcessor 
{
    private BillionsHandler bHandler;
    private MillionsHandler mHandler;
    private ThousandsHandler thHandler;
    private HundredsHandler hHandler;
    private TensHandler teHandler;
    private UnitsHandler uHandler;
    // internal state
    private boolean readyToWork;

    
    public DigitsProcessor(BillionsHandler bHandler, MillionsHandler mHandler, 
            ThousandsHandler thHandler, HundredsHandler hHandler, 
            TensHandler teHandler, UnitsHandler uHandler) 
    {
        this.readyToWork = false;
        
        this.bHandler = bHandler;
        this.mHandler = mHandler;
        this.thHandler = thHandler;
        this.hHandler = hHandler;
        this.teHandler = teHandler;
        this.uHandler = uHandler;
    }
    
    /**
     * A call to this method is needed to initialize the pipeline and make the
     * processor ready to work.
     * 
     * @throws SpellOutException 
     */
    public void buildPipeline() throws SpellOutException
    {
        // idempotence: pipeline built only 1 time
        if(this.readyToWork)
            return;
        // sanity checks on provided inputs
        if(this.bHandler == null || this.mHandler == null || this.teHandler == null 
                || this.hHandler == null || this.thHandler == null || this.uHandler == null)
            throw new SpellOutException("Unable to create the pipeline: one or more handlers"
                    + " are NULL.");
        // actually, building the pipeline
        this.hHandler.setPipeSuccessor(this.teHandler);
        this.teHandler.setPipeSuccessor(this.uHandler);
        this.bHandler.setPipeSuccessor(this.mHandler);
        this.bHandler.setCompositeHandler(this.hHandler);
        this.mHandler.setPipeSuccessor(this.thHandler);
        this.mHandler.setCompositeHandler(this.hHandler);
        this.thHandler.setPipeSuccessor(this.hHandler);
        this.thHandler.setCompositeHandler(this.hHandler);
        // currently, it's ready to accept requests
        this.readyToWork = true;
    }
    
    /**
     * Process the billions
     * 
     * @param digits Array of Digits
     * @param results IO parameter returning the results
     * @throws SpellOutException 
     */
    public void processBillions(int[] digits, Queue<SpellResult> results) 
            throws SpellOutException
    {
        // check whether or not it's ready to work
        this.checkInitialization();
        // process the request by forwarding to the specific handler
        System.out.println("[DigitsProcessor][processBillions] Request to process "
                + "["+digits.length+"]digits");
        System.out.println("IN >>>");
        this.bHandler.manageDigits(digits, results);
        System.out.println("<<< OUT");
    }
    
    /**
     * Process the Millions
     * 
     * @param digits Array of Digits
     * @param results IO parameter returning the results
     * @throws SpellOutException 
     */
    public void processMillions(int[] digits, Queue<SpellResult> results) throws SpellOutException
    {
        // check whether or not it's ready to work
        this.checkInitialization();
        // process the request by forwarding to the specific handler
        System.out.println("[DigitsProcessor][processMillions] Request to process "
                + "["+digits.length+"]digits");
        System.out.println("IN >>>");
        this.mHandler.manageDigits(digits, results);
        System.out.println("<<< OUT");
    }
    
    /**
     * Process the Thousands
     * 
     * @param digits Array of Digits
     * @param results IO parameter returning the results
     * @throws SpellOutException 
     */
    public void processThousands(int[] digits, Queue<SpellResult> results) throws SpellOutException
    {
        // check whether or not it's ready to work
        this.checkInitialization();
        // process the request by forwarding to the specific handler
        System.out.println("[DigitsProcessor][processThousands] Request to process "
                + "["+digits.length+"]digits");
        System.out.println("IN >>>");
        this.thHandler.manageDigits(digits, results);
        System.out.println("<<< OUT");
    }
    
    /**
     * Process the Hundreds
     * 
     * @param digits Array of Digits
     * @param results IO parameter returning the results
     * @throws SpellOutException 
     */
    public void processHundreds(int[] digits, Queue<SpellResult> results) throws SpellOutException
    {
        // check whether or not it's ready to work
        this.checkInitialization();
        // process the request by forwarding to the specific handler
        System.out.println("[DigitsProcessor][processHundreds] Request to process "
                + "["+digits.length+"]digits");
        System.out.println("IN >>>");
        this.hHandler.manageDigits(digits, results);
        System.out.println("<<< OUT");
    }
    
    /**
     * Process the Tens
     * 
     * @param digits Array of Digits
     * @param results IO parameter returning the results
     * @throws SpellOutException 
     */
    public void processTens(int[] digits, Queue<SpellResult> results) throws SpellOutException
    {
        // check whether or not it's ready to work
        this.checkInitialization();
        // process the request by forwarding to the specific handler
        System.out.println("[DigitsProcessor][processTens] Request to process "
                + "["+digits.length+"]digits");
        System.out.println("IN >>>");
        this.teHandler.manageDigits(digits, results);
        System.out.println("<<< OUT");
    }
    
    /**
     * Process the Units
     * 
     * @param digits Array of Digits
     * @param results IO parameter returning the results
     * @throws SpellOutException 
     */
    public void processUnits(int[] digits, Queue<SpellResult> results) throws SpellOutException
    {
        // check whether or not it's ready to work
        this.checkInitialization();
        // process the request by forwarding to the specific handler
        System.out.println("[DigitsProcessor][processUnits] Request to process "
                + "["+digits.length+"]digits");
        System.out.println("IN >>>");
        this.uHandler.manageDigits(digits, results);
        System.out.println("<<< OUT");
    }
    
    private void checkInitialization() throws SpellOutException
    {
        if(!this.readyToWork)
            throw new SpellOutException("Before to use it, the processor must be"
                    + " initialized by building the pipeline.");
    }
    
}
