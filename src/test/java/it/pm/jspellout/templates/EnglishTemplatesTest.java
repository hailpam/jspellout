
package it.pm.jspellout.templates;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 * English templating module Unit Test.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishTemplatesTest extends TestCase 
{
    
    private EnglishTemplates enTemplating;
    
    public EnglishTemplatesTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        this.enTemplating = new EnglishTemplates();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of applyTemplate method, of class EnglishTemplates.
     */
    public void testApplyTemplate() throws Exception 
    {
        System.out.println("[UnitTesting][EnglishTemplates][testApplyTemplate]");
        EnglishSpellResult numbPart = null;
        String result = "";
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            fail();
        }catch(SpellOutException soe) {
        }
        // test 8 trillions
        numbPart = new EnglishSpellResult(false, false);
        numbPart.setFigure(8);
        numbPart.setMagnitude(OrdersOfMagnitude.TRILLIONS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            fail();
        }catch(SpellOutException soe) {
        }
        // test 8 units
        numbPart.setMagnitude(OrdersOfMagnitude.UNITS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "eight ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 11 - tens
        numbPart.setFigure(11);
        numbPart.setMagnitude(OrdersOfMagnitude.TENS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            fail();
        }catch(SpellOutException soe) {
        }
        // test 11 - tens - special case
        numbPart.setSpecialCase(true);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "eleven ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 30 - tens - special case
        numbPart.setFigure(30);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "thirty ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 31 - tens - normal case
        numbPart.setSpecialCase(false);
        numbPart.setFigure(3);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "thirty-");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 100 - hundreds - normal case
        numbPart.setFigure(1);
        numbPart.setMagnitude(OrdersOfMagnitude.HUNDREDS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "a hundred and ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 200 - hundreds - normal case
        numbPart.setFigure(2);
        numbPart.setMagnitude(OrdersOfMagnitude.HUNDREDS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "two hundreds and ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 200 - hundreds - special case
        numbPart.setFigure(200);
        numbPart.setSpecialCase(true);
        numbPart.setMagnitude(OrdersOfMagnitude.HUNDREDS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            fail();
        }catch(SpellOutException soe) {
        }
        // test 200 - hundreds - special case
        numbPart.setFigure(2);
        numbPart.setSpecialCase(true);
        numbPart.setMagnitude(OrdersOfMagnitude.HUNDREDS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "two hundreds ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 200 - hundreds - composite case
        numbPart.setFigure(200);
        numbPart.setSpecialCase(false);
        numbPart.setCompositeCase(true);
        numbPart.setMagnitude(OrdersOfMagnitude.HUNDREDS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "hundreds ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 12345 - thousands
        numbPart.setFigure(12345);
        numbPart.setCompositeCase(false);
        numbPart.setMagnitude(OrdersOfMagnitude.THOUSANDS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "thousands ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 1234567 - millions
        numbPart.setFigure(1234567);
        numbPart.setMagnitude(OrdersOfMagnitude.MILLIONS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "millions ");
        }catch(SpellOutException soe) {
            fail();
        }
        // test 1234567890 - billions
        numbPart.setFigure(1234567890);
        numbPart.setMagnitude(OrdersOfMagnitude.BILLIONS);
        try {
            result = this.enTemplating.applyTemplate(numbPart);
            assertEquals(result, "billions ");
        }catch(SpellOutException soe) {
            fail();
        }
    }
}
