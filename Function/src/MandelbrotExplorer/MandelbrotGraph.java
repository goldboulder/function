/*

 */
package MandelbrotExplorer;

import Graphing.Graphable2D;
import Other.ComplexNumber;
import Other.RectangularBoundry;
import Other.UsefulThings;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Nathan
 */
public class MandelbrotGraph implements Graphable2D{
    
    private int iterations;
    
    public MandelbrotGraph(int iterations){
        this.iterations = iterations;
    }
    
    public int getIterations(){
        return iterations;
    }
    
    public void setIterations(int i){
        iterations = i;
        if (iterations < 0){
            iterations = 0;
        }
    }

    @Override
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry) {//store previous picture? bitmap?
        
        drawFromScratch(g,panelBounds,boundry);
        
        
    }
    
    private void drawFromScratch(Graphics g, Dimension panelBounds, RectangularBoundry boundry){//clipping rectangle?
        double[] xValues = UsefulThings.getXPixelValues(panelBounds,boundry);
        double[] yValues = UsefulThings.getYPixelValues(panelBounds,boundry);
        ComplexNumber c;
        for (int i = 0; i < xValues.length; i++){
            for (int j = 0; j < yValues.length; j++){
                c = new ComplexNumber(xValues[i],yValues[j]);
                if (c.inMandelbrotSet(iterations)){
                    g.drawRect(i, j, 0, 0);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry, double time) {
        draw(g,panelBounds,boundry);
    }
    
    public void userZoomed(){
        
    }
    
    public void userScrolled(int x, int y){
        
    }
    
}
