
package ConcreteFunction;

import CoreFunctions.MathFunction;
import CoreFunctions.Addition;
import CoreFunctions.Constant;
import CoreFunctions.Division;
import CoreFunctions.Exponentiation;
import CoreFunctions.SingleOutputFunction;
import CoreFunctions.SquareRoot;
import Other.MathPoint;
import Other.RectangularBoundry;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import Graphing.NonParametric2DFunction;
import Graphing.ResolutionFunction2D;
import Other.Resolution1D;
import Other.UsefulThings;
import CoreFunctions.Expression;


public class PolarFunction2D implements MathFunction, NonParametric2DFunction, ResolutionFunction2D{

    private SingleOutputFunction function;//composition
    private SingleOutputFunction derivative;// lazy evaluation
    private SingleOutputFunction polarIntegralFunction; 
    private SingleOutputFunction arcLengthFunction;
    public static final Color DEFAULT_COLOR = Color.BLUE;
    private Color color = DEFAULT_COLOR;
    private Resolution1D resolution = new Resolution1D('o',0,2*Math.PI,360);
    // array of values for reuse when graphing?
    
    public PolarFunction2D(String str){
        function = new SingleOutputFunction(str);
        checkVariables();
        
    }
    
    public PolarFunction2D(String str, Color c){
        function = new SingleOutputFunction(str);
        checkVariables();
        this.color = c;
    }
    
    public PolarFunction2D(SingleOutputFunction f){
        function = f;
        checkVariables();
    }
    
    public PolarFunction2D(SingleOutputFunction f, Color c){
        function = f;
        checkVariables();
        this.color = c;
    }
    
    private void checkVariables(){
        /*
        if (!function.getVariables().contains('o') && function.numArguments() >= 1){
            throw new RuntimeException("PolarFunction2D only accepts 'o' as a variable");
        }
*/
    }

    @Override
    public Double value(double x) {
        return function.value(new MathPoint(new char[] {'o'},new Double[]{x}));
    }
    
    @Override
    public Double xComponent(double o){
        return function.value(new MathPoint(new char[] {'o'},new Double[]{o})) * Math.cos(o);
    }
    
    @Override
    public Double yComponent(double o){
        return function.value(new MathPoint(new char[] {'o'},new Double[]{o})) * Math.sin(o);
    }
    
    @Override
    public Double xComponent(double o, double time) {
        return function.value(new MathPoint(new char[] {'o','s'},new Double[]{o,time})) * Math.cos(o);
    }

    @Override
    public Double yComponent(double o, double time) {
        return function.value(new MathPoint(new char[] {'o','s'},new Double[]{o,time})) * Math.sin(o);
    }

    public Double derivative(double o) {
        if (derivative == null){
            derivative = function.derivative('o');
        }
        return derivative.value(new MathPoint(new char[] {'o'},new Double[]{o}));
        //return function.derivative(new MathVector(new char[]{'x'},new Double[]{1.0}), new MathPoint(new char[] {'x'},new Double[]{x}));
    }
    
    
    public RectangularFunction2D derivative(){
        if (derivative == null){
            derivative = function.derivative('o');
        }
        return new RectangularFunction2D(derivative);// no new?
    }
    
    @Override
    public Double dYdX(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double integral(double lowerBound, double upperBound) {//use rectangular function for it
        if (polarIntegralFunction == null){
            Expression f1 = new Exponentiation(function,new Constant(2.0));
            f1 = new Division(f1,new Constant(2.0));
            polarIntegralFunction = new SingleOutputFunction(f1);
        }
        return UsefulThings.integral(polarIntegralFunction, lowerBound, upperBound, 'o');
    }
    
    @Override
    public Double arcLength(double min, double max) {
        if (derivative == null){
            derivative = function.derivative('o');
        }
        
        if (arcLengthFunction == null){
            Expression f1 = new Exponentiation(derivative,new Constant(2.0));
            Expression f2 = new Exponentiation(function,new Constant(2.0));
            f1 = new Addition(f1,f2);
            f1 = new SquareRoot(f1);
            arcLengthFunction = new SingleOutputFunction(f1);
        }
        
        return UsefulThings.integral(arcLengthFunction, min, max, 'o');
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    
    @Override
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry) {
        UsefulThings.drawResolutionFunction(this, g, panelBounds, boundry, resolution);
    }
    
    @Override
    public void draw(Graphics g, Dimension panelBounds, RectangularBoundry boundry, double time) {
        UsefulThings.drawResolutionFunction(this, g, panelBounds, boundry, resolution,time);
    }
    
    @Override
    public void setStart(double start) {
        resolution.setMin(start);
    }

    @Override
    public void setEnd(double end) {
        resolution.setMax(end);
    }

    @Override
    public void setSteps(int steps) {
        resolution.setSteps(steps);
    }
    
    @Override
    public double getStart() {
        return resolution.getMin();
    }

    @Override
    public double getEnd() {
       return resolution.getMax();
    }

    @Override
    public int getSteps() {
        return resolution.getSteps();
    }
    
    @Override
    public String toString(){
        return function.toString();
    }

    @Override
    public Color getColor() {
        return color;
    }

    

    

}
