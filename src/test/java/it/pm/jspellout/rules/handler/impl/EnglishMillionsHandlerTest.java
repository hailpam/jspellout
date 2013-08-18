/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.ArrayDeque;
import java.util.Queue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author hailpam
 */
public class EnglishMillionsHandlerTest extends TestCase {
    
    private EnglishMillionsHandler mHandler;
    private EnglishThousandsHandler thHandler;
    private EnglishHundredsHandler hHandler;
    private EnglishTensHandler tHandler;
    private EnglishUnitsHandler uHandler;
    
    
    public EnglishMillionsHandlerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.mHandler = new EnglishMillionsHandler();
        this.thHandler = new EnglishThousandsHandler();
        this.hHandler = new EnglishHundredsHandler();
        this.tHandler = new EnglishTensHandler();
        this.uHandler = new EnglishUnitsHandler();
        this.tHandler.setPipeSuccessor(uHandler);
        this.hHandler.setPipeSuccessor(tHandler);
        this.thHandler.setPipeSuccessor(hHandler);
        this.thHandler.setCompositeHandler(hHandler);
        this.mHandler.setPipeSuccessor(thHandler);
        this.mHandler.setCompositeHandler(hHandler);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of manageDigits method, of class EnglishMillionsHandler.
     */
    public void testManageDigits() throws Exception 
    {
        System.out.println("[UnitTesting][EnglishMillionsHandler][testManageDigits]");
        int[] digits = new int[1];
        Queue<SpellResult> results = null;
        try {
            this.mHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[14];
        results = new ArrayDeque<SpellResult>();
        try {
            this.mHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[9];
        results = null;
        try {
            this.mHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[7];
        digits[0] = 2;
        digits[1] = 3;
        digits[2] = 4;
        digits[3] = 1;
        digits[4] = 2;
        digits[5] = 3;
        digits[6] = 6;
        results = new ArrayDeque<SpellResult>();
        try {
            this.mHandler.manageDigits(digits, results);
            assertTrue(results.size() == 9);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 6);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 6);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.MILLIONS);
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 3);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 321);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.THOUSANDS);
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 4);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 3);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
    }
}
