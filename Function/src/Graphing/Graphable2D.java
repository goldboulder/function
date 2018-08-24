
package Graphing;

import Other.RectangularBoundry;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public interface Graphable2D extends Graphable{
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry);
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry, double time);
}
