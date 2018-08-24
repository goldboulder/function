
package Graphing;

import Other.RectangularBoundry;
import Other.UsefulThings;
import static Other.UsefulThings.graphCoToScreenCoX;
import static Other.UsefulThings.graphCoToScreenCoY;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class RectangularGridPainter2D extends GridPainter2D{

    
    
    
    @Override
    public void draw(Graphics g,Dimension panelBounds, RectangularBoundry boundry) {
        //unpackage variables
        double xMin = boundry.getBoundry('x').getLowerBound();
        double xMax = boundry.getBoundry('x').getUpperBound();
        double yMin = boundry.getBoundry('y').getLowerBound();
        double yMax = boundry.getBoundry('y').getUpperBound();
        int xPanelSize = panelBounds.width;
        int yPanelSize = panelBounds.height;
        double scale = UsefulThings.determineScale(boundry);
        int x = graphCoToScreenCoX(0,xPanelSize,xMin,xMax);
        int y = graphCoToScreenCoY(0,yPanelSize,yMin,yMax);
        
        drawVerticalLines(g,yMin,yMax,x,xPanelSize,yPanelSize,scale);
        drawHorizontalLines(g,xMin,xMax,y,xPanelSize,yPanelSize,scale);
        
    }
    
    @Override
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry, double time) {
        draw(g,panelBounds,boundry);
    }
    
    private void drawHorizontalLines(Graphics g, double xMin, double xMax, int y, int xSize, int ySize, double scale){
        double xMark = UsefulThings.roundToScale(xMin,scale);
        
        while (xMark <= xMax){
            int xMarkInt = graphCoToScreenCoX(xMark,xSize,xMin,xMax);
            
            g.setColor(colorForMarking(xMark, scale));
            
            // write milestone numbers
            if (g.getColor() == majorGridColor){
                g.setColor(tickColor);
                g.drawString(Double.toString(UsefulThings.format(xMark)), xMarkInt, 14);
                g.drawString(Double.toString(UsefulThings.format(xMark)), xMarkInt, ySize - 40);
                g.setColor(majorGridColor);
            }
            
            g.drawLine(xMarkInt, 0, xMarkInt, ySize);
            
            //draw tick marks
            g.setColor(tickColor);
            g.drawLine(xMarkInt, y-3, xMarkInt, y+3);
            
            xMark += scale;
        }
    }
    
    private void drawVerticalLines(Graphics g, double yMin, double yMax, int x, int xSize, int ySize, double scale){
        double yMark = UsefulThings.roundToScale(yMin,scale);
        while (yMark <= yMax){
            int yMarkInt = graphCoToScreenCoY(yMark,ySize,yMin,yMax);
            
            g.setColor(colorForMarking(yMark, scale));
            
            // write milestone numbers
            if (g.getColor() == majorGridColor){
                g.setColor(tickColor);
                g.drawString(Double.toString(UsefulThings.format(yMark)), 3, yMarkInt);
                //g.drawString(Double.toString(UsefulThings.format(yMark)), xSize - 45 - extraOffset(yMark), yMarkInt);
                g.setColor(majorGridColor);
            }
            
            
            g.drawLine(0, yMarkInt, xSize, yMarkInt);
            
            //draw tick marks
            g.setColor(tickColor);
            g.drawLine(x-3, yMarkInt, x+3, yMarkInt);
            
            yMark += scale;
        }
    }

    
        
    
    
    
    
    

    
}
