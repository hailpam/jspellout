
package it.pm.jspellout.rules;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.Queue;
import java.util.Random;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 * English rules Unit Test.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishRulesTest extends TestCase {
    
    private EnglishRules enRules;
    
    public EnglishRulesTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.enRules = new EnglishRules();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of applyRules method, of class EnglishRules.
     */
    public void testApplyRules() throws Exception {
        System.out.println("[UnitTesting][EnglishRules][testApplyRules]");
        int[] digits = null;
        try {
            this.enRules.applyRules(digits);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[0];
        try {
            this.enRules.applyRules(digits);
            fail();
        }catch(SpellOutException soe) {
        }
        digits = new int[14];
        try {
            this.enRules.applyRules(digits);
            fail();
        }catch(SpellOutException soe) {
        }
        // test billions
        Queue<SpellResult> results = null;
        Random rGenerator = new Random();
        digits = new int[12];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = rGenerator.nextInt(10);
        }
        try {
            results = this.enRules.applyRules(digits);
            assertTrue(results.size() > 0);
        }catch(SpellOutException soe) {
            fail();
        }
        // test millions
        digits = new int[8];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = rGenerator.nextInt(10);
        }
        try {
            results = this.enRules.applyRules(digits);
            assertTrue(results.size() > 0);
        }catch(SpellOutException soe) {
            fail();
        }
        // test thousands
        digits = new int[6];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = rGenerator.nextInt(10);
        }
        try {
            results = this.enRules.applyRules(digits);
            assertTrue(results.size() > 0);
        }catch(SpellOutException soe) {
            fail();
        }
        // test hundreds
        digits = new int[3];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = rGenerator.nextInt(10);
        }
        try {
            results = this.enRules.applyRules(digits);
            assertTrue(results.size() > 0);
        }catch(SpellOutException soe) {
            fail();
        }
        // test tens
        digits = new int[2];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = rGenerator.nextInt(10);
        }
        try {
            results = this.enRules.applyRules(digits);
            assertTrue(results.size() > 0);
        }catch(SpellOutException soe) {
            fail();
        }
        // test units
        digits = new int[1];
        for(int i = 0; i < digits.length; i++) {
            digits[i] = rGenerator.nextInt(10);
        }
        try {
            results = this.enRules.applyRules(digits);
            assertTrue(results.size() > 0);
        }catch(SpellOutException soe) {
            fail();
        }
    }
}
