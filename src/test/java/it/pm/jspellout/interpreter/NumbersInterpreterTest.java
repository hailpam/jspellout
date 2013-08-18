
package it.pm.jspellout.interpreter;

import it.pm.jspellout.model.SpellOutException;
import java.math.BigInteger;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 * Numbers Interpreter Unit Testing class.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class NumbersInterpreterTest extends TestCase {
    
    public NumbersInterpreterTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getInstance method, of class NumbersInterpreter.
     */
    public void testGetInstance() 
    {
        System.out.println("[UnitTesting][NumbersInterpreterHandler][getInstance]");
        NumbersInterpreter result = NumbersInterpreter.getInstance();
        assertTrue(result != null);
        assertTrue(result instanceof NumbersInterpreter);
        
    }

    /**
     * Test of englishSpellOut method, of class NumbersInterpreter.
     */
    public void testEnglishSpellOut() throws Exception 
    {
        
        System.out.println("[UnitTesting][NumbersInterpreter][englishSpellOut]");
        int number = 0;
        NumbersInterpreter instance = NumbersInterpreter.getInstance();
        String result = "";
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "zero ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 10
        number = 10;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "ten ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 11
        number = 11;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 31
        number = 31;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "thirty-one ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 30
        number = 30;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "thirty ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 97
        number = 97;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "ninety-seven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 100
        number = 100;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "a hundred ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 900
        number = 900;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "nine hundreds ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 105
        number = 105;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "a hundred and five ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 297
        number = 297;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "two hundreds and ninety-seven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 220
        number = 220;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "two hundreds and twenty ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 1220
        number = 1220;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "one thousand two hundreds and twenty ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 7211
        number = 7211;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "seven thousands two hundreds and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 17211
        number = 17211;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "seventeen thousands two hundreds and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 99211
        number = 99211;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "ninety-nine thousands two hundreds and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 199211
        number = 199211;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "a hundred and ninety-nine thousands two hundreds and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 23199211
        number = 23199211;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "twenty-three millions a hundred and ninety-nine thousands two hundreds and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 723199211
        number = 723199211;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "seven hundreds and twenty-three millions a hundred and ninety-nine thousands two hundreds and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 56945781
        number = 56945781;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "fifty-six millions nine hundreds and forty-five thousands seven hundreds and eighty-one ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 10 100 100
        number = 10100100;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "ten millions a hundred thousands a hundred ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 99 999 999
        number = 99999999;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "ninety-nine millions nine hundreds and ninety-nine thousands nine hundreds and ninety-nine ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 00 000 000
        number = 00000000;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "zero ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 11 111 111
        number = 11111111;
        try {
            result = instance.englishSpellOut(number);
            assertEquals(result, "eleven millions a hundred and eleven thousands a hundred and eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 199 274 141
        long lNumber = 199274141;
         try {
            result = instance.englishSpellOut(lNumber);
            assertEquals(result, "a hundred and ninety-nine millions two hundreds and seventy-four thousands a hundred and forty-one ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 999 999 999
        lNumber = 999999999;
        try {
            result = instance.englishSpellOut(lNumber);
            assertEquals(result, "nine hundreds and ninety-nine millions nine hundreds and ninety-nine thousands nine hundreds and ninety-nine ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 23 999 999 999
        BigInteger bInt = new BigInteger("23999999999");
        try {
            result = instance.englishSpellOut(bInt.longValue());
            assertEquals(result, "twenty-three billions nine hundreds and ninety-nine millions nine hundreds and ninety-nine thousands nine hundreds and ninety-nine ");
        }catch(SpellOutException soe) {
            fail();
        }
    }

    /**
     * Test of italianSpellOut method, of class NumbersInterpreter.
     */
    public void testItalianSpellOut() throws Exception 
    {
        System.out.println("[UnitTesting][NumbersInterpreter][italianSpellOut]");
        int number = 0;
        NumbersInterpreter instance = NumbersInterpreter.getInstance();
        String result = null;
        try {
            result = instance.italianSpellOut(number);
            fail();
        }catch(SpellOutException soe) {
        }catch(UnsupportedOperationException uoe) {
        }
        
    }

    /**
     * Test of franceSpellOut method, of class NumbersInterpreter.
     */
    public void testFrenchSpellOut() throws Exception 
    {
        System.out.println("[UnitTesting][NumbersInterpreter][franceSpellOut]");
        int number = 0;
        NumbersInterpreter instance = NumbersInterpreter.getInstance();
        String result = null;
        try {
            result = instance.frenchSpellOut(number);
            fail();
        }catch(SpellOutException soe) {
        }catch(UnsupportedOperationException uoe) {
        }
        
    }

    /**
     * Test of germanSpellOut method, of class NumbersInterpreter.
     */
    public void testGermanSpellOut() throws Exception 
    {
        System.out.println("[UnitTesting][NumbersInterpreter][germanSpellOut]");
        int number = 0;
        NumbersInterpreter instance = NumbersInterpreter.getInstance();
        String result = null;
        try {
            result = instance.germanSpellOut(number);
            fail();
        }catch(SpellOutException soe) {
        }catch(UnsupportedOperationException uoe) {
        }
        
    }
    
}
