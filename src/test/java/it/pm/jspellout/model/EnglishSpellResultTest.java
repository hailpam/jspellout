
package it.pm.jspellout.model;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 * English Spell Result Unit Testing class.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishSpellResultTest extends TestCase {
    
    private EnglishSpellResult spellRest;
    
    public EnglishSpellResultTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.spellRest = new EnglishSpellResult(false, false);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGettersAndSetters() {
        System.out.println("[UnitTesting][EnglishSpellResult][testGettersAndSetters]");
        assertEquals(this.spellRest.getFigure(), new Integer(0));
        this.spellRest.setFigure(10);
        assertEquals(this.spellRest.getFigure(), new Integer(10));
        assertEquals(this.spellRest.getMagnitude(), OrdersOfMagnitude.UNITS);
        this.spellRest.setMagnitude(OrdersOfMagnitude.TRILLIONS);
        assertEquals(this.spellRest.getMagnitude(), OrdersOfMagnitude.TRILLIONS);
    }
    
    public void testToString() {
        System.out.println("[UnitTesting][EnglishSpellResult][testToString]");
        String expRes = "SpellResult{figure=0, magnitude=UNITS}";
        expRes += "EnglishSpellResult{" + "specialCase=false, "
                + "compositeCase=false}";
        assertEquals(expRes, this.spellRest.toString());
    }
    
}
