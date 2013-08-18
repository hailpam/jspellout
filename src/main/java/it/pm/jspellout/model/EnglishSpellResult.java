
package it.pm.jspellout.model;

/**
 * English Spell Results. Basically, inherits the behaviour for the abstract 
 * class (anything to add).
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public class EnglishSpellResult extends SpellResult
{    
    private boolean specialCase;
    private boolean compositeCase;

    public EnglishSpellResult(boolean specialCase, boolean compositeCase) 
    {
        super();
        this.specialCase = specialCase;
        this.compositeCase = compositeCase;
    }

    public boolean isSpecialCase() 
    {
        return specialCase;
    }

    public void setSpecialCase(boolean specialCase) 
    {
        this.specialCase = specialCase;
    }

    public boolean isCompositeCase() 
    {
        return compositeCase;
    }

    public void setCompositeCase(boolean compositeCase) 
    {
        this.compositeCase = compositeCase;
    }

    @Override
    public String toString() {
        return super.toString() + 
                "EnglishSpellResult{" + "specialCase=" + specialCase + ", "
                + "compositeCase=" + compositeCase + '}';
    }
    
}
