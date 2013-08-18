
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
 * English billions (specific handler) Unit Test.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishBillionsHandlerTest extends TestCase 
{
    
    private EnglishBillionsHandler bHandler;
    private EnglishMillionsHandler mHandler;
    private EnglishThousandsHandler thHandler;
    private EnglishHundredsHandler hHandler;
    private EnglishTensHandler tHandler;
    private EnglishUnitsHandler uHandler;
    
    public EnglishBillionsHandlerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.bHandler = new EnglishBillionsHandler();
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
        this.bHandler.setPipeSuccessor(mHandler);
        this.bHandler.setCompositeHandler(hHandler);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of manageDigits method, of class EnglishBillionsHandler.
     */
    public void testManageDigits() throws Exception 
    {
        System.out.println("[UnitTesting][EnglishBillionsHandler][testManageDigits]");
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
        digits = new int[12];
        results = null;
        try {
            this.mHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[12];
        digits[0] = 2;
        digits[1] = 3;
        digits[2] = 4;
        digits[3] = 1;
        digits[4] = 2;
        digits[5] = 3;
        digits[6] = 6;
        digits[7] = 7;
        digits[8] = 8;
        digits[9] = 1;
        digits[10] = 2;
        digits[11] = 3;
        results = new ArrayDeque<SpellResult>();
        try {
            this.bHandler.manageDigits(digits, results);
            assertTrue(results.size() == 15);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 3);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
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
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.BILLIONS);
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 8);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 7);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 6);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 876);
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
