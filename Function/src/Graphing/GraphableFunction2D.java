
package Graphing;

import java.awt.Color;


public interface GraphableFunction2D extends Graphable2D{
    public Color getColor();
    public void setColor(Color c);
    public Double dYdX(double t);
    public Double arcLength(double min, double max);
    public Double xComponent(double arg);
    public Double yComponent(double arg);
    public Double xComponent(double arg, double time);
    public Double yComponent(double arg, double time);

}
