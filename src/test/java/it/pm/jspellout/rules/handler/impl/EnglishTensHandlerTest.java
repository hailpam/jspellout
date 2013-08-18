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
 * English Tens (specific handler) Unit Test.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishTensHandlerTest extends TestCase {
    
    private EnglishTensHandler tHandler;
    private EnglishUnitsHandler uHandler;
    
    public EnglishTensHandlerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.tHandler = new EnglishTensHandler();
        this.uHandler = new EnglishUnitsHandler();
        this.tHandler.setPipeSuccessor(this.uHandler);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of manageDigits method, of class EnglishTensHandler.
     */
    public void testManageDigits() throws Exception {
        System.out.println("[UnitTesting][EnglishTensHandler][testManageDigits]");
        int[] digits = new int[1];
        Queue<SpellResult> results = null;
        try {
            this.tHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[3];
        results = new ArrayDeque<SpellResult>();
        try {
            this.tHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[2];
        // test 11
        digits[0] = 1;
        digits[1] = 1;
        try {
            this.tHandler.manageDigits(digits, results);
            assertTrue(results.size() == 1);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 11);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 21
        digits[0] = 1;
        digits[1] = 2;
        try {
            this.tHandler.manageDigits(digits, results);
            assertTrue(results.size() == 2);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
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
            fail();
        }
        // test 30
        digits[0] = 0;
        digits[1] = 3;
        try {
            this.tHandler.manageDigits(digits, results);
            assertTrue(results.size() == 1);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 30);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.TENS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 09
        digits[0] = 9;
        digits[1] = 0;
        try {
            this.tHandler.manageDigits(digits, results);
            assertTrue(results.size() == 1);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude() == OrdersOfMagnitude.UNITS);
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
    }
}
