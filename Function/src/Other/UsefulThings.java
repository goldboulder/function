
package Other;

import CoreFunctions.SingleOutputFunction;
import Graphing.GraphableFunction2D;
import java.awt.Dimension;
import java.awt.Graphics;


public class UsefulThings {
    
    // rounds a number to 4 decimal places if > 1, 4 sig figs if less than 1
    public static double format(double num){
        if(Math.abs(num) > 1){
            num = Math.round(num*10000)/10000.0;
        }
        else{
            double mult = Math.pow(10,(int)(4 - Math.log10(Math.abs(num))));
            num = Math.round(num*mult)/mult;
        }
        return num;
    }
    
    public static boolean isMilestone(double tickMark, double scale, int count){
        //if (Math.abs(tickMark) >= 1){
        //    return (tickMark % (scale*count) == 0);
        //}
        //else{
            double mod = Math.abs(tickMark % (scale*count));
            //System.out.println(tickMark + "   " + Math.abs(tickMark % (scale*count)));
            return (mod < 1E-13 || mod > scale*count - 1E-13);
        //}
        
    }
    
    public static double determineScale(RectangularBoundry graphBounds){//delete?
        double log = Math.log10(graphBounds.getBoundry('x').spaceCovered() * 0.2);
        int rounded = (int)Math.round(log-0.5);
        return Math.pow(10, rounded);
    }
    
    public static double determineScale(double min, double max){
        double log = Math.log10((max - min) * 0.2);
        int rounded = (int)Math.round(log-0.5);
        return Math.pow(10, rounded);
    }
    
    public static double roundToScale(double num, double scale){
        return scale * (int)(num / scale);
    }
    
    public static int graphCoToScreenCoX(double xPoint, int xPanelSize, double xMin, double xMax){
        //if (xPoint < xMin)
            //return 0;
        //if (xPoint > xMax)
            //return xPanelSize;
        if (xPoint > Double.NEGATIVE_INFINITY){
            double percentage = (xPoint - xMin)/(xMax - xMin);
            return (int)(percentage * xPanelSize);
        }
        else
            return Integer.MIN_VALUE;
    }
    
    public static int graphCoToScreenCoY(double yPoint, int ySize, double yMin, double yMax){
        //if (yPoint < yMax)
            //return ySize;
        //if (yPoint > yMax)
            //return 0;
        if (yPoint > Double.NEGATIVE_INFINITY){
            double percentage = 1-((yPoint - yMin)/(yMax - yMin));
            return (int)(percentage * ySize);
        }
        else
            return Integer.MIN_VALUE;
    }
    
    public static double screenCoToGraphCoX(int mouseX, int panelWidth, RectangularBoundry boundry){
        double percent = mouseX/(double)panelWidth;
        double xSize = boundry.getBoundry('x').spaceCovered();
        return boundry.getBoundry('x').getLowerBound() + (xSize * percent);
    }
    public static double screenCoToGraphCoY(int mouseY, int panelHeight, RectangularBoundry boundry){
        double percent = mouseY/(double)panelHeight;
        double ySize = boundry.getBoundry('y').spaceCovered();
        return boundry.getBoundry('y').getUpperBound() - (ySize * percent);
    }
    
    
    // return a numerical approximation for the finite integral of a function
    // using Simpson's rule
    public static Double integral(SingleOutputFunction function, double lowerBound, double upperBound, char letter){
        
        int n = 100;  // must be even
        Double h = (upperBound - lowerBound)/(n);
        Double[][] points = new Double[n+1][2];
        for (int i = 0; i < points.length; i++){
            points[i][0] = lowerBound + i*h;
        }
        
        for (int i = 0; i < points.length; i++){
            points[i][1] = function.value(new MathPoint(new char[]{letter},new Double[]{points[i][0]}));//new MathPoint(new char[]{letter},new Double[]{points[i][0]})
            //System.out.println(function + "   " + points[i][1]);
        }
        
        Double sumOdd = 0.0;
        Double sumEven = 0.0;
        
        for (int i = 1; i <= points.length / 2; i++)
            sumOdd += points[2 * i - 1][1];
        for (int i = 1; i <= points.length / 2 - 1; i++)
            sumEven += points[2 * i][1];
        
        sumOdd *= 4;
        sumEven *= 2;
        
        return (sumOdd + sumEven + points[0][1] + points[points.length - 1][1]) * h / 3;
    }
    
    
    public static void drawResolutionFunction(GraphableFunction2D function, Graphics g, Dimension d, RectangularBoundry b, Resolution1D r){
        double[][] graphPoints = getPoints(function,d,r);
        int[][] screenPoints = graphPointsToScreenPoints(graphPoints,d,b);
        g.setColor(function.getColor());
        for(int i = 0; i < screenPoints.length - 1; i++){
            if (isValidPointsDouble(graphPoints[i][0],graphPoints[i+1][0]) && isValidPointsDouble(graphPoints[i][1],graphPoints[i+1][1])){
                g.drawLine(screenPoints[i][0],screenPoints[i][1],screenPoints[i+1][0],screenPoints[i+1][1]);
                //System.out.println(screenPoints[i][0] + "   " + screenPoints[i][1]);
            }
        }
    }
    
    public static void drawResolutionFunction(GraphableFunction2D function, Graphics g, Dimension d, RectangularBoundry b, Resolution1D r, double time){
        double[][] graphPoints = getPoints(function,d,r,time);
        int[][] screenPoints = graphPointsToScreenPoints(graphPoints,d,b);
        g.setColor(function.getColor());
        for(int i = 0; i < screenPoints.length - 1; i++){
            if (isValidPointsDouble(graphPoints[i][0],graphPoints[i+1][0]) && isValidPointsDouble(graphPoints[i][1],graphPoints[i+1][1])){
                g.drawLine(screenPoints[i][0],screenPoints[i][1],screenPoints[i+1][0],screenPoints[i+1][1]);
            }
        }
    }
    
    private static boolean isValidPointsInt(int n1, int n2, int n3, int n4){
        return (n1 != Integer.MIN_VALUE && n1 != Integer.MAX_VALUE &&
                n2 != Integer.MIN_VALUE && n2 != Integer.MAX_VALUE &&
                n3 != Integer.MIN_VALUE && n3 != Integer.MAX_VALUE &&
                n4 != Integer.MIN_VALUE && n4 != Integer.MAX_VALUE);
    }
    
    private static boolean isValidPointsDouble(double n1, double n2){
        return (n1 > Integer.MIN_VALUE && n1 < Integer.MAX_VALUE &&
                n2 > Integer.MIN_VALUE && n2 < Integer.MAX_VALUE);
    }
    
    private static double[][] getPoints(GraphableFunction2D f, Dimension d, Resolution1D r){
        double currentT = r.getMin();
        double[][] points = new double[d.width][2];//width == 0?
        
        for (int i = 0; i < points.length; i++){
            points[i][0] = f.xComponent(currentT);
            points[i][1] = f.yComponent(currentT);
            currentT += r.stepLength();
        }
        
        return points;
    }
    
    private static double[][] getPoints(GraphableFunction2D f, Dimension d, Resolution1D r, double time){
        double currentT = r.getMin();
        double[][] points = new double[r.getSteps()][2];//width == 0?
        
        for (int i = 0; i < points.length; i++){
            points[i][0] = f.xComponent(currentT,time);
            points[i][1] = f.yComponent(currentT,time);
            currentT += r.stepLength();
        }
        
        return points;
    }
    
    private static int[][] graphPointsToScreenPoints(double[][] graphPoints, Dimension d, RectangularBoundry b){
        int[][] screenPoints = new int[graphPoints.length][2];
        for (int i = 0; i < graphPoints.length; i++){
            //System.out.println(graphPoints[i][1]);
            int[] intPoints = scaleToIntegerForDrawing(graphPoints[i][0],graphPoints[i][1],d,b);
            screenPoints[i][0] = intPoints[0];
            screenPoints[i][1] = intPoints[1];
        }
        return screenPoints;
    }
    
    
    
    public static int[] scaleToIntegerForDrawing(double x, double y,Dimension d, RectangularBoundry b){
        double xMin = b.getBoundry('x').getLowerBound();
        double xMax = b.getBoundry('x').getUpperBound();
        double yMin = b.getBoundry('y').getLowerBound();
        double yMax = b.getBoundry('y').getUpperBound();
        //System.out.println(y);
        double screenX = (x - xMin)/(xMax - xMin)*d.width;
        double screenY = (1-((y - yMin)/(yMax - yMin)))*d.width;
        //System.out.println(screenY);
        
        if (!isValidPointsDouble(screenX,screenY)){
            double angle = Math.atan2(screenY, screenX);
            int screenSize = d.width + d.height;
            screenX = screenSize*Math.cos(angle);
            screenY = screenSize * Math.sin(angle);
        }
        
        return new int[]{(int)screenX,(int)screenY};
        
    }

    public static double[] getXPixelValues(Dimension panelBounds, RectangularBoundry boundry) {
        
        double[] values = new double[panelBounds.height];
        double xMin = boundry.getBoundry('x').getLowerBound();
        
        values[0] = xMin;
        
        double dx = (boundry.getBoundry('x').getUpperBound()-boundry.getBoundry('x').getLowerBound())/panelBounds.width;
        for (int i = 1; i < values.length; i++){
            values[i] = values[i-1] + dx;
        }
        
        return values;
    }

    public static double[] getYPixelValues(Dimension panelBounds, RectangularBoundry boundry) {
        double[] values = new double[panelBounds.height];
        double yMax = boundry.getBoundry('y').getUpperBound();//y is inversed on screen space
        values[0] = yMax;
        double dy = (boundry.getBoundry('y').getUpperBound()-boundry.getBoundry('y').getLowerBound())/panelBounds.height;
        for (int i = 1; i < values.length; i++){
            values[i] = values[i-1] - dy;
        }
        return values;
    }
}
