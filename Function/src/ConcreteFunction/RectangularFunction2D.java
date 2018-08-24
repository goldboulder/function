
package ConcreteFunction;

import CoreFunctions.MathFunction;
import CoreFunctions.Addition;
import CoreFunctions.SingleOutputFunction;
import CoreFunctions.Constant;
import CoreFunctions.Exponentiation;
import CoreFunctions.SquareRoot;
import Other.MathPoint;
import Other.RectangularBoundry;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import Graphing.NonParametric2DFunction;
import Other.UsefulThings;
import CoreFunctions.Expression;


public class RectangularFunction2D implements MathFunction, NonParametric2DFunction{
    protected SingleOutputFunction function;//composition
    private SingleOutputFunction derivative;// lazy evaluation
    private SingleOutputFunction arcLengthFunction;
    public static final Color DEFAULT_COLOR = Color.RED;
    private Color color = DEFAULT_COLOR;
    
    public RectangularFunction2D(String str){
        function = new SingleOutputFunction(str);
        checkVariables();
    }
    
    public RectangularFunction2D(String str, Color c){
        function = new SingleOutputFunction(str);
        checkVariables();
        this.color = c;
    }
    
    public RectangularFunction2D(SingleOutputFunction f){
        function = f;
        checkVariables();
    }
    
    public RectangularFunction2D(SingleOutputFunction f, Color c){
        function = f;
        checkVariables();
        this.color = c;
    }
    
    protected void checkVariables(){
        //if (!function.getVariables().contains('x') && function.numArguments() >= 1){
            //throw new RuntimeException("RectangularFunction2D only accepts 'x' as a variable");
        //}
    }

    @Override
    public Double value(double x) {
        return function.value(new MathPoint(new char[] {'x'},new Double[]{x}));
    }
    
    public Double value(double x, double time) {
        return function.value(new MathPoint(new char[] {'x','s'},new Double[]{x,time}));
    }

    @Override
    public Double derivative(double x) {
        if (derivative == null){
            derivative = function.derivative('x');
        }
        return derivative.value(new MathPoint(new char[] {'x'},new Double[]{x}));
        //return function.derivative(new MathVector(new char[]{'x'},new Double[]{1.0}), new MathPoint(new char[] {'x'},new Double[]{x}));
    }
    
    public RectangularFunction2D derivative(){
        if (derivative == null){
            derivative = function.derivative('x');
        }
        return new RectangularFunction2D(derivative);
    }

    
    @Override
    public Double dYdX(double t) {
        return derivative(t);
    }
    @Override
    public Double integral(double lowerBound, double upperBound){
        return UsefulThings.integral(function,lowerBound,upperBound,'x');
    }
    
    @Override
    public Double arcLength(double min, double max) {
        if (derivative == null){
            derivative = function.derivative('x');
        }
        
        if (arcLengthFunction == null){
            Expression f1 = new Exponentiation(derivative,new Constant(2.0));
            f1 = new Addition(f1,new Constant(1.0));
            f1 = new SquareRoot(f1);
            arcLengthFunction = new SingleOutputFunction(f1);
        }
        return UsefulThings.integral(arcLengthFunction, min, max, 'x');
    }
    

    public RectangularFunction2D integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public RectangularFunction2D taylorSeries(double base, int maxDegree){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void draw(Graphics g, Dimension d, RectangularBoundry boundry) {//optimized for knowing the x screen value
        double[] graphPoints = getPoints(d,boundry);
        int[] screenPoints = graphPointsToScreenPoints(graphPoints,d,boundry);
        g.setColor(color);
        for(int i = 0; i < screenPoints.length - 1; i++){
            if (isValidPoints(screenPoints[i],screenPoints[i+1])){// if number
                g.drawLine(i,screenPoints[i],i+1,screenPoints[i+1]);
            }
        }
    }
    
    //for animated functions
    @Override
    public void draw(Graphics g, Dimension d, RectangularBoundry boundry, double time) {//optimized for knowing the x screen value
        double[] graphPoints = getPoints(d,boundry,time);
        int[] screenPoints = graphPointsToScreenPoints(graphPoints,d,boundry);
        g.setColor(color);
        for(int i = 0; i < screenPoints.length - 1; i++){
            if (isValidPoints(screenPoints[i],screenPoints[i+1])){// if number
                g.drawLine(i,screenPoints[i],i+1,screenPoints[i+1]);
            }
        }
    }
    
    private boolean isValidPoints(int p1, int p2){
        return (p1 != Integer.MIN_VALUE && p2 != Integer.MIN_VALUE && p1 != Integer.MAX_VALUE && p2 != Integer.MAX_VALUE);
    }
    
    //returns array of y coodinates
    private double[] getPoints(Dimension d, RectangularBoundry boundry){
        double currentX = boundry.getBoundry('x').getLowerBound();
        double step = (boundry.getBoundry('x').spaceCovered())/(d.width);
        double[] points = new double[d.width];//width == 0?
        
        for (int i = 0; i < points.length; i++){
            points[i] = value(currentX);
            currentX += step;
        }
        
        return points;
    }
    
    //returns array of y coodinates
    private double[] getPoints(Dimension d, RectangularBoundry boundry, double time){
        double currentX = boundry.getBoundry('x').getLowerBound();
        double step = (boundry.getBoundry('x').spaceCovered())/(d.width);
        double[] points = new double[d.width];//width == 0?
        
        for (int i = 0; i < points.length; i++){
            points[i] = value(currentX,time);
            currentX += step;
        }
        
        return points;
    }
    
    private int[] graphPointsToScreenPoints(double[] graphPoints, Dimension d, RectangularBoundry boundry){
        int[] screenPoints = new int[d.width];
        for (int i = 0; i < graphPoints.length; i++){
            screenPoints[i] = UsefulThings.graphCoToScreenCoY(graphPoints[i],d.height,boundry.getBoundry('y').getLowerBound(),boundry.getBoundry('y').getUpperBound());
        }
        return screenPoints;
    }
    
    @Override
    public String toString(){
        return function.toString();
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Double xComponent(double arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double yComponent(double arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double xComponent(double arg, double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double yComponent(double arg, double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
