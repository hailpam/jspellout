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
 * English thousands (specific handler) Unit Test.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishThousandsHandlerTest extends TestCase {
    
    private EnglishThousandsHandler thHandler;
    private EnglishHundredsHandler hHandler;
    private EnglishTensHandler tHandler;
    private EnglishUnitsHandler uHandler;
    
    public EnglishThousandsHandlerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.thHandler = new EnglishThousandsHandler();
        this.hHandler = new EnglishHundredsHandler();
        this.tHandler = new EnglishTensHandler();
        this.uHandler = new EnglishUnitsHandler();
        this.tHandler.setPipeSuccessor(uHandler);
        this.hHandler.setPipeSuccessor(tHandler);
        this.thHandler.setPipeSuccessor(hHandler);
        this.thHandler.setCompositeHandler(hHandler);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of manageDigits method, of class EnglishThousandsHandler.
     */
    public void testManageDigits() throws Exception 
    {
        System.out.println("[UnitTesting][EnglishThousandsHandler][testManageDigits]");
        int[] digits = new int[1];
        Queue<SpellResult> results = null;
        try {
            this.thHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[7];
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[6];
        results = null;
        try {
            this.thHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        // test 1111
        digits = new int[4];
        digits[0] = 1;
        digits[1] = 1;
        digits[2] = 1;
        digits[3] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 4);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 11);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 1299
        digits = new int[4];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 5);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 9299
        digits = new int[4];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 9;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 5);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 19299
        digits = new int[5];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 9;
        digits[4] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 5);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 19);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 19);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 45299
        digits = new int[5];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 5;
        digits[4] = 4;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 6);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 4);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 5);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 45);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 245299
        digits = new int[6];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 5;
        digits[4] = 4;
        digits[5] = 2;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 7);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 4);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 5);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 245);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 001299
        digits = new int[6];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 1;
        digits[4] = 0;
        digits[5] = 0;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 5);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 011299
        digits = new int[6];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 1;
        digits[4] = 1;
        digits[5] = 0;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 5);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 11);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 11);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 120299
        digits = new int[6];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 0;
        digits[4] = 2;
        digits[5] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 6);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 20);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 120);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
        // test 121299
        digits = new int[6];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 2;
        digits[3] = 1;
        digits[4] = 2;
        digits[5] = 1;
        results = new ArrayDeque<SpellResult>();
        try {
            this.thHandler.manageDigits(digits, results);
            assertTrue(results.size() == 7);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            EnglishSpellResult enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 1);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.UNITS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 121);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS));
            enResult = (EnglishSpellResult) result;
            assertTrue(enResult.isCompositeCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 2);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
            assertTrue(result instanceof EnglishSpellResult);
            assertTrue(result.getFigure().intValue() == 9);
            assertTrue(result.getMagnitude().equals(OrdersOfMagnitude.TENS));
            enResult = (EnglishSpellResult) result;
            assertFalse(enResult.isSpecialCase());
        }catch(SpellOutException soe) {
            fail();
        }
    }
}
