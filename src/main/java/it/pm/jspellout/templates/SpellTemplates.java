
package it.pm.jspellout.templates;

import it.pm.jspellout.model.SpellOutException;
import it.pm.jspellout.model.SpellResult;

/**
 * Defines the interface for the template engines. 
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public interface SpellTemplates 
{
    
    /**
     * Look for a template and apply it to the spell result got in Input.
     * 
     * @param numbPart Spell result containing the information
     * @return String containing the applied template
     * 
     * @throws SpellOutException 
     */
    String applyTemplate(SpellResult numbPart) throws SpellOutException;
    
}
