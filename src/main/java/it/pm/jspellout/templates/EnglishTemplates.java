
package it.pm.jspellout.templates;

import it.pm.jspellout.model.EnglishSpellResult;
import it.pm.jspellout.model.OrdersOfMagnitude;
import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;
import java.util.HashMap;

/**
 *
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishTemplates implements SpellTemplates
{
    // unit translation table
    private String[] uTranslation;
    // tens translation table for normal cases
    private HashMap<Integer, String> teNCTranslation;
    // tens translation table for special cases
    private HashMap<Integer, String> teSCTranslation;
    // hundreds translation strings
    private String hNCTranslation;
    private String hCCTranslation;
    private String hSCTranslation;
    
    public EnglishTemplates() 
    {
        // units, translation array
        this.uTranslation = new String[10];
        this.uTranslation[0] = "zero";
        this.uTranslation[1] = "one";
        this.uTranslation[2] = "two";
        this.uTranslation[3] = "three";
        this.uTranslation[4] = "four";
        this.uTranslation[5] = "five";
        this.uTranslation[6] = "six";
        this.uTranslation[7] = "seven";
        this.uTranslation[8] = "eight";
        this.uTranslation[9] = "nine";
        // tens, translation map - nomal cases
        this.teNCTranslation = new HashMap<Integer, String>();
        this.teNCTranslation.put(2, "twenty-");
        this.teNCTranslation.put(3, "thirty-");
        this.teNCTranslation.put(4, "forty-");
        this.teNCTranslation.put(5, "fifty-");
        this.teNCTranslation.put(6, "sixty-");
        this.teNCTranslation.put(7, "seventy-");
        this.teNCTranslation.put(8, "eighty-");
        this.teNCTranslation.put(9, "ninety-");
        // tens, translation map - nomal cases
        this.teSCTranslation = new HashMap<Integer, String>();
        this.teSCTranslation.put(10, "ten");
        this.teSCTranslation.put(20, "twenty");
        this.teSCTranslation.put(30, "thirty");
        this.teSCTranslation.put(40, "forty");
        this.teSCTranslation.put(50, "fifty");
        this.teSCTranslation.put(60, "sixty");
        this.teSCTranslation.put(70, "seventy");
        this.teSCTranslation.put(80, "eighty");
        this.teSCTranslation.put(90, "ninety");
        this.teSCTranslation.put(11, "eleven");
        this.teSCTranslation.put(12, "twelve");
        this.teSCTranslation.put(13, "thirteen");
        this.teSCTranslation.put(14, "fourteen");
        this.teSCTranslation.put(15, "fifteen");
        this.teSCTranslation.put(16, "sixteen");
        this.teSCTranslation.put(17, "seventeen");
        this.teSCTranslation.put(18, "eighteen");
        this.teSCTranslation.put(19, "nineteen");
        // hundreds, translation strings
        this.hNCTranslation = "{X} {Y} and";
        this.hCCTranslation = "hundred";
        this.hSCTranslation = "{X} {Y}";
    }
    
    
    public String applyTemplate(SpellResult numbPart) throws SpellOutException 
    {
        
        // inputs sanity check
        if(numbPart == null)
            throw new SpellOutException("Expected not NULL spell result entities.");
        EnglishSpellResult enResult = (EnglishSpellResult) numbPart;
        if((enResult.getFigure().intValue() < 0 || enResult.getFigure().intValue() > 9) 
                && (enResult.getMagnitude().equals(OrdersOfMagnitude.UNITS) ||
                (enResult.getMagnitude().equals(OrdersOfMagnitude.TENS) && !enResult.isSpecialCase()) ||
                (enResult.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS) && !enResult.isCompositeCase())))
            throw new SpellOutException("Error: expected figures in the range [0, 9]");
        StringBuilder part = new StringBuilder();
        // parsing and translation
        if(enResult.getMagnitude().equals(OrdersOfMagnitude.UNITS)) {
            System.out.println("[EnglishTemplates][applyTemplate] Order of magnitude "
                    + "["+enResult.getMagnitude()+"] \n\t-->> Translating Units");
            part.append(this.uTranslation[enResult.getFigure().intValue()]);
            part.append(" ");
        }else if(enResult.getMagnitude().equals(OrdersOfMagnitude.TENS)) {
            System.out.println("[EnglishTemplates][applyTemplate] Order of magnitude "
                    + "["+enResult.getMagnitude()+"] \n\t-->> Translating Tens");
            if(enResult.isSpecialCase()) {
                part.append(this.teSCTranslation.get(enResult.getFigure()));
                part.append(" ");
            }else
                part.append(this.teNCTranslation.get(enResult.getFigure()));
        }else if(enResult.getMagnitude().equals(OrdersOfMagnitude.HUNDREDS)) {
            System.out.println("[EnglishTemplates][applyTemplate] Order of magnitude "
                    + "["+enResult.getMagnitude()+"] \n\t-->> Translating Hundreds");
            String tmpStr = "";
            if(enResult.isCompositeCase()) {
                part.append(this.hCCTranslation);
                if(enResult.getFigure().intValue() > 1)
                    part.append("s");
            }else if(enResult.isSpecialCase()) {
                if(enResult.getFigure().intValue() == 1) {
                    tmpStr = this.hSCTranslation.replace("{X}", "a");
                    tmpStr = tmpStr.replace("{Y}", "hundred");
                }else {
                    tmpStr = this.hSCTranslation.replace("{X}", 
                            this.uTranslation[enResult.getFigure().intValue()]);
                    tmpStr = tmpStr.replace("{Y}", "hundreds");
                }
                part.append(tmpStr);
            }else {    
                if(enResult.getFigure().intValue() == 1) {
                    tmpStr = this.hNCTranslation.replace("{X}", "a");
                    tmpStr = tmpStr.replace("{Y}", "hundred");
                }else {
                    tmpStr = this.hNCTranslation.replace("{X}", 
                            this.uTranslation[enResult.getFigure().intValue()]);
                    tmpStr = tmpStr.replace("{Y}", "hundreds");
                }
                part.append(tmpStr);
            }
            part.append(" ");
        }else if(enResult.getMagnitude().equals(OrdersOfMagnitude.THOUSANDS)) {
            System.out.println("[EnglishTemplates][applyTemplate] Order of magnitude "
                    + "["+enResult.getMagnitude()+"] \n\t-->> Translating Thousands");
            part.append("thousand");
            if(enResult.getFigure().intValue() > 1)
                part.append("s");
            part.append(" ");
        }else if(enResult.getMagnitude().equals(OrdersOfMagnitude.MILLIONS)) {
            System.out.println("[EnglishTemplates][applyTemplate] Order of magnitude "
                    + "["+enResult.getMagnitude()+"] \n\t-->> Translating Millions");
            part.append("million");
            if(enResult.getFigure().intValue() > 1)
                part.append("s");
            part.append(" ");
        }else if(enResult.getMagnitude().equals(OrdersOfMagnitude.BILLIONS)) {
            System.out.println("[EnglishTemplates][applyTemplate] Order of magnitude "
                    + "["+enResult.getMagnitude()+"] \n\t-->> Translating Billions");
            part.append("billion");
            if(enResult.getFigure().intValue() > 1)
                part.append("s");
            part.append(" ");
        }else
            throw new SpellOutException("Error: Order of magnitude "
                    + "["+enResult.getMagnitude()+"] not supported yet.");
        
        return part.toString();
    }
    
}
