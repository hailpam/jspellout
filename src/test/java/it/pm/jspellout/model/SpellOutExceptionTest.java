
package it.pm.jspellout.model;

import junit.framework.TestCase;

/**
 * Spell Out Exception Unit Testing class.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class SpellOutExceptionTest extends TestCase {
    
    private SpellOutException spellOutEx;
    private static final String EX_MSG = "This is a simple Test!";
    
    public SpellOutExceptionTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.spellOutEx = new SpellOutException(EX_MSG);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetMessage() {
        System.out.println("[UnitTesting][SpellOutException][testGetMessage]");
        assertEquals(this.spellOutEx.getMessage(), EX_MSG);
        assertFalse(this.spellOutEx.getMessage().equals(new SpellOutException("").getMessage()));
    }
    
}
