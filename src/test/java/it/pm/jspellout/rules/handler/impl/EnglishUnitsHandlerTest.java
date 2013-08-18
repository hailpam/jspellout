
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.ArrayDeque;
import java.util.Queue;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 * English Units handler Unit Test.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishUnitsHandlerTest extends TestCase {
    
    private EnglishUnitsHandler uHandler;
    
    public EnglishUnitsHandlerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.uHandler = new EnglishUnitsHandler();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of manageDigits method, of class EnglishUnitsHandler.
     */
    public void testManageDigits() throws Exception 
    {
        System.out.println("[UnitTesting][EnglishUnitsHandler][testManageDigits]");
        int[] digits = new int[1];
        Queue<SpellResult> results = null;
        try {
            this.uHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[2];
        results = new ArrayDeque<SpellResult>();
        try {
            this.uHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[1];
        digits[0] = 10;
        try {
            this.uHandler.manageDigits(digits, results);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[1];
        digits[0] = 7;
        try {
            this.uHandler.manageDigits(digits, results);
            SpellResult result = results.poll();
            assertTrue(result instanceof EnglishSpellResult);
            assertEquals(result.getFigure(), new Integer(7));
            assertEquals(result.getMagnitude(), OrdersOfMagnitude.UNITS);
        }catch(SpellOutException soe) {
            fail();
        }
    }
}
