
package Graphing;

import Other.RectangularBoundry;
import Other.UsefulThings;
import static Other.UsefulThings.graphCoToScreenCoX;
import static Other.UsefulThings.graphCoToScreenCoY;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class PolarGridPainter2D extends GridPainter2D{

    @Override
    public void draw(Graphics g,Dimension panelBounds, RectangularBoundry boundry) {// don't unwrap
        double xMin = boundry.getBoundry('x').getLowerBound();
        double xMax = boundry.getBoundry('x').getUpperBound();
        double yMin = boundry.getBoundry('y').getLowerBound();
        double yMax = boundry.getBoundry('y').getUpperBound();
        int xPanelSize = panelBounds.width;
        int yPanelSize = panelBounds.height;
        
        int x = graphCoToScreenCoX(0,xPanelSize,xMin,xMax);
        int y = graphCoToScreenCoY(0,yPanelSize,yMin,yMax);
        
        drawCircles(g,xMin,xMax,yMin,yMax,xPanelSize,yPanelSize);
        drawAngleLines(g,xMin,xMax,yMin,yMax,xPanelSize,yPanelSize);
    }
    
    @Override
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry, double time) {
        draw(g,panelBounds,boundry);
    }

    private void drawCircles(Graphics g, double xMin, double xMax, double yMin, double yMax, int xPanelSize, int yPanelSize){
        double rMin = smallestVisibleCircle(xMin,xMax,yMin,yMax);
        double rMax = largestVisibleCircle(xMin,xMax,yMin,yMax);
        double scale = determineScale(xMin,xMax);
        
        
        double rMark = UsefulThings.roundToScale(rMin,scale);
        
        
        int originMarkX = graphCoToScreenCoX(0,xPanelSize,xMin,xMax);
        int originMarkY = graphCoToScreenCoX(0,yPanelSize,yMin,yMax);
        
        while (rMark <= rMax){
            int rMarkInt = graphCoToScreenCoX(rMark,xPanelSize,xMin,xMax) - graphCoToScreenCoX(0,xPanelSize,xMin,xMax);
            
            g.setColor(colorForMarking(rMark, scale));
            
            // drawResolutionFunction numbers?
            //g.drawString(Double.toString(UsefulThings.format(rMark)), rMarkInt, 14);
            
            g.drawOval(originMarkX - rMarkInt, yPanelSize - (originMarkY + rMarkInt), rMarkInt*2, rMarkInt*2);
            
            rMark += scale;
        }

    }
    
    private void drawAngleLines(Graphics g, double xMin, double xMax, double yMin, double yMax, int xPanelSize, int yPanelSize){
        double aMin = getMinAngle(xMin,xMax,yMin,yMax);
        double aMax = getMaxAngle(xMin,xMax,yMin,yMax);
        double scale = determineAngleScale(aMin,aMax,xMin,xMax,yMin,yMax);
        
        double aMark = UsefulThings.roundToScale(aMin,scale);
        
        int originMarkX = graphCoToScreenCoX(0,xPanelSize,xMin,xMax);
        int originMarkY = yPanelSize - graphCoToScreenCoX(0,yPanelSize,yMin,yMax);
        
        while (aMark <= aMax){
            double maxRCoveredByScreen = largestVisibleCircle(xMin,xMax,yMin,yMax);
            double xMaxGridPoint = maxRCoveredByScreen * Math.cos(aMark);
            int xPanelPoint = graphCoToScreenCoX(xMaxGridPoint,xPanelSize,xMin,xMax);
            double yMaxGridPoint = maxRCoveredByScreen * Math.sin(aMark);
            int yPanelPoint = graphCoToScreenCoY(yMaxGridPoint,yPanelSize,yMin,yMax);
            
            g.setColor(minorGridColor);
            
            // drawResolutionFunction numbers?
            //g.drawString(Double.toString(UsefulThings.format(rMark)), rMarkInt, 14);
            
            g.drawLine(originMarkX,originMarkY,xPanelPoint,yPanelPoint);
            
            aMark += scale;
        }
    }

    
    private double smallestVisibleCircle(double xMin, double xMax, double yMin, double yMax){
        //origin is visible
        if ((xMin < 0 && xMax > 0) && (yMin < 0 && yMax > 0)){
            return 0;
        }
        //numpad: orgin is key 5
        
        else if (xMax < 0 && yMin > 0){//7
            return Math.hypot(xMax, yMin);
        }
        else if (yMin > 0 && (xMin < 0 && xMax > 0)){//8
            return yMin;
        }
        else if (xMin > 0 && yMin > 0){//9
            return Math.hypot(xMin, yMin);
        }
        else if (xMax < 0 && (yMin < 0 && yMax > 0)){//4
            return xMax;
        }
        else if (xMin > 0 && (yMin < 0 && yMax > 0)){//6
            return xMin;
        }
        else if (xMax < 0 && yMax < 0){//1
            return Math.hypot(xMax, yMax);
        }
        else if (yMax < 0 && (xMin < 0 && xMax > 0)){//2
            return yMax;
        }
        else{//3
            return Math.hypot(xMin, yMax);
        }
        
    }
    
    private double largestVisibleCircle(double xMin, double xMax, double yMin, double yMax){
        double topLeft = Math.hypot(xMin, yMax);
        double topRight = Math.hypot(xMax, yMax);
        double bottomLeft = Math.hypot(xMin, yMin);
        double bottomRight = Math.hypot(xMax, yMin);
        
        double top = Math.max(topLeft, topRight);
        double bottom = Math.max(bottomLeft, bottomRight);
        
        return Math.max(top,bottom);
    }
    
    private double determineScale(double min, double max){
        double log = Math.log10((max - min) * 0.2);
        int rounded = (int)Math.round(log-0.5);
        //System.out.println(min + " " + max + " " + Math.pow(10, rounded));
        return Math.pow(10, rounded);
    }

    private double getMinAngle(double xMin, double xMax, double yMin, double yMax) {
        if (xMax > 0 && yMin < 0 && yMax > 0){
            return 0;
        }
        
        double topLeft = Math.atan2(yMax, xMin);
        if (topLeft < 0) topLeft += 2*Math.PI;
        double topRight = Math.atan2(yMax, xMax);
        if (topRight < 0) topRight += 2*Math.PI;
        double bottomLeft = Math.atan2(yMin, xMin);
        if (bottomLeft < 0) bottomLeft += 2*Math.PI;
        double bottomRight = Math.atan2(yMin, xMax);
        if (bottomRight < 0) bottomRight += 2*Math.PI;
        
        double top = Math.min(topLeft, topRight);
        double bottom = Math.min(bottomLeft, bottomRight);
        
        return Math.min(top, bottom);
        
        
    }

    private double getMaxAngle(double xMin, double xMax, double yMin, double yMax) {
        if (xMax > 0 && yMin < 0 && yMax > 0){
            return 2*Math.PI;
        }
        
        double topLeft = Math.atan2(yMax, xMin);
        if (topLeft < 0) topLeft += 2*Math.PI;
        double topRight = Math.atan2(yMax, xMax);
        if (topRight < 0) topRight += 2*Math.PI;
        double bottomLeft = Math.atan2(yMin, xMin);
        if (bottomLeft < 0) bottomLeft += 2*Math.PI;
        double bottomRight = Math.atan2(yMin, xMax);
        if (bottomRight < 0) bottomRight += 2*Math.PI;
        
        double top = Math.max(topLeft, topRight);
        double bottom = Math.max(bottomLeft, bottomRight);
        
        return Math.max(top, bottom);
    }

    private double determineAngleScale(double aMin, double aMax, double xMin, double xMax, double yMin, double yMax) {
        if (xMin < 0 && xMax > 0 && yMin < 0 && yMax > 0){
            return Math.toRadians(15);
        }
        
        //screen is on +x axis and zoomed in
        if (xMax > 0 && yMin < 0 && yMax > 0){
            double aMin2 = Math.atan2(yMax, xMin);
            double aMax2 = Math.atan2(yMin,xMin);
            return Math.toRadians(UsefulThings.determineScale(Math.toDegrees(aMax2),Math.toDegrees(aMin2)));
        }
        
        
        return Math.toRadians(UsefulThings.determineScale(Math.toDegrees(aMin),Math.toDegrees(aMax)));
    }

    

    
}