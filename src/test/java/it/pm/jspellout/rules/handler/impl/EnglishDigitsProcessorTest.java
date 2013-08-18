
package it.pm.jspellout.rules.handler.impl;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.ArrayDeque;
import java.util.Queue;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author hailpam
 */
public class EnglishDigitsProcessorTest extends TestCase {
    
    EnglishDigitsProcessor enDigitProc;
    
    public EnglishDigitsProcessorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.enDigitProc = new EnglishDigitsProcessor();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBuildPipeline() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testBuildPipeline]");
        try {
            this.enDigitProc.processBillions(new int[10], new ArrayDeque<SpellResult>());
            fail();
        }catch(SpellOutException soe) {
        }
        try {
            this.enDigitProc.buildPipeline();
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
    public void testProcessBillions() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testProcessBillions]");
        // test 100 999 030 102
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        int[] digits = new int[12];
        digits[0] = 2;
        digits[1] = 0;
        digits[2] = 1;
        digits[3] = 0;
        digits[4] = 3;
        digits[5] = 0;
        digits[6] = 9;
        digits[7] = 9;
        digits[8] = 9;
        digits[9] = 0;
        digits[10] = 0;
        digits[11] = 1;
        try {
            this.enDigitProc.buildPipeline();
            this.enDigitProc.processBillions(digits, results);
            assertTrue(results.size() == 10);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100 000 000 000
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 0;
        digits[3] = 0;
        digits[4] = 0;
        digits[5] = 0;
        digits[6] = 0;
        digits[7] = 0;
        digits[8] = 0;
        digits[9] = 0;
        digits[10] = 0;
        digits[11] = 1;
        try {
            this.enDigitProc.processBillions(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100 100 100 100
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 1;
        digits[3] = 0;
        digits[4] = 0;
        digits[5] = 1;
        digits[6] = 0;
        digits[7] = 0;
        digits[8] = 1;
        digits[9] = 0;
        digits[10] = 0;
        digits[11] = 1;
        try {
            this.enDigitProc.processBillions(digits, results);
            assertTrue(results.size() == 7);
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
    public void testProcessMillions() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testProcessMillions]");
        // test 999 030 102
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        int[] digits = new int[9];
        digits[0] = 2;
        digits[1] = 0;
        digits[2] = 1;
        digits[3] = 0;
        digits[4] = 3;
        digits[5] = 0;
        digits[6] = 9;
        digits[7] = 9;
        digits[8] = 9;
        try {
            this.enDigitProc.buildPipeline();
            this.enDigitProc.processMillions(digits, results);
            assertTrue(results.size() == 8);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100 000 000
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 0;
        digits[3] = 0;
        digits[4] = 0;
        digits[5] = 0;
        digits[6] = 0;
        digits[7] = 0;
        digits[8] = 1;
        try {
            this.enDigitProc.processMillions(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100 100 100
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 1;
        digits[3] = 0;
        digits[4] = 0;
        digits[5] = 1;
        digits[6] = 0;
        digits[7] = 0;
        digits[8] = 1;
        try {
            this.enDigitProc.processMillions(digits, results);
            assertTrue(results.size() == 5);
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
    public void testProcessThousands() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testProcessThousands]");
        // test 999 030
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        int[] digits = new int[6];
        digits[0] = 0;
        digits[1] = 3;
        digits[2] = 0;
        digits[3] = 9;
        digits[4] = 9;
        digits[5] = 9;
        try {
            this.enDigitProc.buildPipeline();
            this.enDigitProc.processThousands(digits, results);
            assertTrue(results.size() == 5);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100 000
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 0;
        digits[3] = 0;
        digits[4] = 0;
        digits[5] = 1;
        try {
            this.enDigitProc.processThousands(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100 100
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 1;
        digits[3] = 0;
        digits[4] = 0;
        digits[5] = 1;
        try {
            this.enDigitProc.processThousands(digits, results);
            assertTrue(results.size() == 3);
        }catch(SpellOutException soe) {
            fail();
        }
        
        // 021 100
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 1;
        digits[3] = 1;
        digits[4] = 2;
        digits[5] = 0;
        try {
            this.enDigitProc.processThousands(digits, results);
            assertTrue(results.size() == 4);
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
    public void testProcessHundreds() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testProcessHundreds]");
        // test 999
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        int[] digits = new int[3];
        digits[0] = 9;
        digits[1] = 9;
        digits[2] = 9;
        try {
            this.enDigitProc.buildPipeline();
            this.enDigitProc.processHundreds(digits, results);
            assertTrue(results.size() == 3);
        }catch(SpellOutException soe) {
            fail();
        }
        // 100
        results.clear();
        digits[0] = 0;
        digits[1] = 0;
        digits[2] = 1;
        try {
            this.enDigitProc.processHundreds(digits, results);
            assertTrue(results.size() == 1);
        }catch(SpellOutException soe) {
            fail();
        }
        // 101
        results.clear();
        digits[0] = 1;
        digits[1] = 0;
        digits[2] = 1;
        try {
            this.enDigitProc.processHundreds(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 111
        results.clear();
        digits[0] = 1;
        digits[1] = 1;
        digits[2] = 1;
        try {
            this.enDigitProc.processHundreds(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 141
        results.clear();
        digits[0] = 1;
        digits[1] = 4;
        digits[2] = 1;
        try {
            this.enDigitProc.processHundreds(digits, results);
            assertTrue(results.size() == 3);
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
    public void testProcessTens() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testProcessTens]");
        // test 99
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        int[] digits = new int[2];
        digits[0] = 9;
        digits[1] = 9;
        try {
            this.enDigitProc.buildPipeline();
            this.enDigitProc.processTens(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 10
        results.clear();
        digits[0] = 0;
        digits[1] = 1;
        try {
            this.enDigitProc.processTens(digits, results);
            assertTrue(results.size() == 1);
        }catch(SpellOutException soe) {
            fail();
        }
        // 11
        results.clear();
        digits[0] = 1;
        digits[1] = 1;
        try {
            this.enDigitProc.processTens(digits, results);
            assertTrue(results.size() == 1);
        }catch(SpellOutException soe) {
            fail();
        }
        // 31
        results.clear();
        digits[0] = 1;
        digits[1] = 3;
        try {
            this.enDigitProc.processTens(digits, results);
            assertTrue(results.size() == 2);
        }catch(SpellOutException soe) {
            fail();
        }
        // 70
        results.clear();
        digits[0] = 0;
        digits[1] = 7;
        try {
            this.enDigitProc.processTens(digits, results);
            assertTrue(results.size() == 1);
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
    public void testProcessUnits() 
    {
        System.out.println("[UnitTesting][EnglishDigitsProcessor][testProcessUnits]");
        // test 99
        Queue<SpellResult> results = new ArrayDeque<SpellResult>();
        int[] digits = new int[1];
        digits[0] = 9;
        try {
            this.enDigitProc.buildPipeline();
            this.enDigitProc.processUnits(digits, results);
            assertTrue(results.size() == 1);
        }catch(SpellOutException soe) {
            fail();
        }
    }
    
}
