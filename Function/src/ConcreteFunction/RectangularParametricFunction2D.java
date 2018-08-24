
package ConcreteFunction;

import CoreFunctions.MathFunction;
import CoreFunctions.Addition;
import CoreFunctions.Constant;
import CoreFunctions.Exponentiation;
import CoreFunctions.SingleOutputFunction;
import CoreFunctions.SquareRoot;
import Graphing.Parametric2DFunction;
import Graphing.ResolutionFunction2D;
import Other.MathPoint;
import Other.RectangularBoundry;
import Other.Resolution1D;
import Other.UsefulThings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import CoreFunctions.Expression;


public class RectangularParametricFunction2D implements MathFunction, Parametric2DFunction, ResolutionFunction2D{

    private SingleOutputFunction xFunction;//composition
    private SingleOutputFunction xDerivative;// lazy evaluation
    private SingleOutputFunction yFunction;//composition
    private SingleOutputFunction yDerivative;// lazy evaluation
    private SingleOutputFunction arcLengthFunction;
    public static final Color DEFAULT_COLOR = Color.GREEN;
    private Color color = DEFAULT_COLOR;
    private Resolution1D resolution = new Resolution1D('t',0,6.28,100);
    
    public RectangularParametricFunction2D(String strX, String strY){
        xFunction = new SingleOutputFunction(strX);
        yFunction = new SingleOutputFunction(strY);
        checkVariables();
        
    }
    
    public RectangularParametricFunction2D(String strX, String strY, Color c){
        xFunction = new SingleOutputFunction(strX);
        yFunction = new SingleOutputFunction(strY);
        checkVariables();
        this.color = c;
    }
    
    public RectangularParametricFunction2D(SingleOutputFunction xFunction, SingleOutputFunction yFunction){
        this.xFunction = xFunction;
        this.yFunction = yFunction;
        checkVariables();
    }
    
    public RectangularParametricFunction2D(SingleOutputFunction xFunction, SingleOutputFunction yFunction, Color c){
        this.xFunction = xFunction;
        this.yFunction = yFunction;
        checkVariables();
        this.color = c;
    }
    
    private void checkVariables(){
        /*
        if (!xFunction.getVariables().contains('t') && xFunction.numArguments() >= 1
                || !yFunction.getVariables().contains('t') && yFunction.numArguments() >= 1){
            throw new RuntimeException("RectangularParametricFunction2D only accepts 't' as a variable");
        }
*/
    }
    
    @Override
    public void setSubFunction(char c, SingleOutputFunction f) {
        if (c == 'x'){
            xFunction = f;
            xDerivative = null;
        }
        else if (c == 'y'){
            yFunction = f;
            xDerivative = null;
        }
        else{
            throw new RuntimeException("only the characters 'x' and 'y' are allowed here");
        }
        checkVariables();
    }
    
    @Override
    public MathPoint value(double t) {
        return new MathPoint(new char[] {'x','y'},new Double[]{xValue(t),yValue(t)});
    }
    
    @Override
    public Double xValue(double t) {
        return xFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
    }

    @Override
    public Double yValue(double t) {
        return yFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
    }
    
    @Override
    public Double oValue(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double rValue(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(char c, double t) {
        if (c == 'x'){
            if (xDerivative == null){
                xDerivative = xFunction.derivative('t');
            }
            return xDerivative.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        }
        else if (c == 'y'){
            if (yDerivative == null){
                yDerivative = yFunction.derivative('t');
            }
            return yDerivative.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        }
        else{
            throw new RuntimeException("only the characters 'x' and 'y' are allowed here");
        }
    }
    
    @Override
    public Double arcLength(double min, double max) {
        if (xDerivative == null){
            xDerivative = xFunction.derivative('t');
        }
        
        if (yDerivative == null){
            yDerivative = yFunction.derivative('t');
        }
        
        if (arcLengthFunction == null){
            Expression f1 = new Exponentiation(xDerivative,new Constant(2.0));
            Expression f2 = new Exponentiation(yDerivative,new Constant(2.0));
            f1 = new Addition(f1,f2);
            f1 = new SquareRoot(f1);
            arcLengthFunction = new SingleOutputFunction(f1);
        }
        
        return UsefulThings.integral(arcLengthFunction, min, max, 't');
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Double dYdX(double t) {
        return derivative('y',t) / derivative('x',t);
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
        return "x = " + xFunction.toString() + "  y = " + yFunction.toString();
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    

    @Override
    public Double xComponent(double arg) {
        return xFunction.value(new MathPoint(new char[] {'t'},new Double[]{arg}));
    }

    @Override
    public Double yComponent(double arg) {
        return yFunction.value(new MathPoint(new char[] {'t'},new Double[]{arg}));
    }

    @Override
    public Double xComponent(double arg, double time) {
        return xFunction.value(new MathPoint(new char[] {'t','s'},new Double[]{arg,time}));
    }

    @Override
    public Double yComponent(double arg, double time) {
        return yFunction.value(new MathPoint(new char[] {'t','s'},new Double[]{arg,time}));
        
    }

    
    
}
