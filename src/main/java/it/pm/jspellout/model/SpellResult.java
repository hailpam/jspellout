
package it.pm.jspellout.model;

/**
 * Defines the product entity created by the handlers. It works as a template
 * model to be specialized for each specific language spelling.
 * 
 * @author Paolo Maresca <plo.maresca@gmail.com>
 */
public abstract class SpellResult
{
    private Integer figure;
    private OrdersOfMagnitude magnitude;
    
    public SpellResult() {
        this.figure = 0;
        this.magnitude = OrdersOfMagnitude.UNITS;
    }

    public Integer getFigure() {
        return figure;
    }

    public void setFigure(Integer figure) {
        this.figure = figure;
    }

    public OrdersOfMagnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(OrdersOfMagnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public String toString() {
        return "SpellResult{" + "figure=" + figure + ", magnitude=" + magnitude + '}';
    }
    
}
