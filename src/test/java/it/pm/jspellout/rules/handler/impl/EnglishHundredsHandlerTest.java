
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
 * English Hundreds (specific handler) Unit Test.
 *  
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishHundredsHandlerTest extends TestCase {
    
    private EnglishHundredsHandler hHandler;
    private EnglishTensHandler tHandler;
    private EnglishUnitsHandler uHandler;
    
    public EnglishHundredsHandlerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.hHandler = new EnglishHundredsHandler();
        this.tHandler = new EnglishTensHandler();
        this.uHandler = new EnglishUnitsHandler();
        this.tHandler.setPipeSuccessor(uHandler);
        this.hHandler.setPipeSuccessor(tHandler);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of manageDigits method, of class EnglishHundredsHandler.
     */
    public void testManageDigits() throws Exception 
    {
        System.out.println("[UnitTesting][EnglishHundredsHandler][testManageDigits]");
        int[] digits = new int[1];
        Queue<SpellResult> results = null;
        try {
            this.hHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[4];
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[3];
        results = null;
        try {
            this.hHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        // test 100
        digits = new int[3];
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 1);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 900
        digits = new int[3];
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 9;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 1);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 999
        digits = new int[3];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 9;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 3);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 111
        digits = new int[3];
        digits[0] = 1;
        digits[1] = 1;
        digits[2] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 2);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 11);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 460
        digits = new int[3];
        digits[0] = 0;
        digits[1] = 6;
        digits[2] = 4;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 2);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 4);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 60);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 461
        digits = new int[3];
        digits[0] = 1;
        digits[1] = 6;
        digits[2] = 4;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 3);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 4);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 6);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 301
        digits = new int[3];
        digits[0] = 1;
        digits[1] = 0;
        digits[2] = 3;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 2);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 3);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 031
        digits = new int[3];
        digits[0] = 1;
        digits[1] = 3;
        digits[2] = 0;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 2);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 3);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 511
        digits = new int[3];
        digits[0] = 1;
        digits[1] = 1;
        digits[2] = 5;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 2);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 5);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.HUNDREDS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 11);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
        // test 001
        digits = new int[3];
        digits[0] = 1;
        digits[1] = 0;
        digits[2] = 0;
        results = new ArrayDeque<SpellResult>();
        try {
            this.hHandler.manageDigits(digits, results);
            assertTrue(results.size() == 1);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
        }
    }
}
