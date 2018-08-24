/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteFunction;

import CoreFunctions.MathFunction;
import CoreFunctions.Addition;
import CoreFunctions.Constant;
import CoreFunctions.Exponentiation;
import CoreFunctions.Multiplication;
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


public class PolarParametricFunction2D implements MathFunction, Parametric2DFunction, ResolutionFunction2D{
    
    private SingleOutputFunction oFunction;//composition
    private SingleOutputFunction rFunction;
    private SingleOutputFunction oDerivative; 
    private SingleOutputFunction rDerivative;// lazy evaluation
    private SingleOutputFunction arcLengthFunction;
    public static final Color DEFAULT_COLOR = new Color(140,0,140);
    private Color color = DEFAULT_COLOR;
    private Resolution1D resolution = new Resolution1D('t',0,2*Math.PI,360);
    
    public PolarParametricFunction2D(String strO, String strR){
        oFunction = new SingleOutputFunction(strO);
        rFunction = new SingleOutputFunction(strR);
        checkVariables();
        
    }
    
    public PolarParametricFunction2D(String strO, String strR, Color c){
        oFunction = new SingleOutputFunction(strO);
        rFunction = new SingleOutputFunction(strR);
        checkVariables();
        this.color = c;
    }
    
    public PolarParametricFunction2D(SingleOutputFunction oFunction, SingleOutputFunction rFunction){
        this.oFunction = oFunction;
        this.rFunction = rFunction;
        checkVariables();
    }
    
    public PolarParametricFunction2D(SingleOutputFunction oFunction, SingleOutputFunction rFunction, Color c){
        this.oFunction = oFunction;
        this.rFunction = rFunction;
        checkVariables();
        this.color = c;
    }
    
    private void checkVariables(){
        /*
        if (!oFunction.getVariables().contains('t') && oFunction.numArguments() >= 1
                || !rFunction.getVariables().contains('t') && rFunction.numArguments() >= 1){
            throw new RuntimeException("PolarParametricFunction2D only accepts 't' as a variable");
        }
*/
    }

    @Override
    public Double xValue(double t) {
        double r = rFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        double theta = oFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        return r * Math.cos(theta);
    }

    @Override
    public Double yValue(double t) {
        double r = rFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        double theta = oFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        return r * Math.sin(theta);
    }
    
    public Double oValue(double t){
        return oFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
    }
    
    public Double rValue(double t){
        return rFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
    }

    @Override
    public MathPoint value(double t) {
        double r = rFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        double theta = oFunction.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        return new MathPoint(new char[] {'x','y'},new Double[]{r * Math.cos(theta),r * Math.sin(theta)});
    }

    @Override
    public Double derivative(char c, double t) {
        if (c == 'o'){
            if (oDerivative == null){
                oDerivative = oFunction.derivative('t');
            }
            return oDerivative.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        }
        else if (c == 'r'){
            if (rDerivative == null){
                rDerivative = rFunction.derivative('t');
            }
            return rDerivative.value(new MathPoint(new char[] {'t'},new Double[]{t}));
        }
        else{
            throw new RuntimeException("only the characters 'o' and 'r' are allowed here");
        }
    }

    @Override
    public void setSubFunction(char c, SingleOutputFunction f) {
        if (c == 'o'){
            oFunction = f;
        }
        else if (c == 'r'){
            rFunction = f;
        }
        else{
            throw new RuntimeException("only the characters 'o' and 'r' are allowed here");
        }
        checkVariables();
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Double dYdX(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double arcLength(double min, double max) {
        if (oDerivative == null){
            oDerivative = oFunction.derivative('t');
        }
        
        if (rDerivative == null){
            rDerivative = rFunction.derivative('t');
        }
        
        if (arcLengthFunction == null){
            Expression f1 = new Exponentiation(rDerivative,new Constant(2.0));
            Expression f2 = new Multiplication(oDerivative,rFunction);
            f2 = new Exponentiation(f2,new Constant(2.0));
            f1 = new Addition(f1,f2);
            f1 = new SquareRoot(f1);
            arcLengthFunction = new SingleOutputFunction(f1);
        }
        
        return UsefulThings.integral(arcLengthFunction, min, max, 't');
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
        return "o = " + oFunction.toString() + "  r = " + rFunction.toString();
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    

    @Override
    public Double xComponent(double arg) {
        return rFunction.value(new MathPoint(new char[] {'t'},new Double[]{arg})) * Math.cos(oFunction.value(new MathPoint(new char[] {'t'},new Double[]{arg})));
    }

    @Override
    public Double yComponent(double arg) {
        return rFunction.value(new MathPoint(new char[] {'t'},new Double[]{arg})) * Math.sin(oFunction.value(new MathPoint(new char[] {'t'},new Double[]{arg})));
    }

    @Override
    public Double xComponent(double arg, double time) {
        return rFunction.value(new MathPoint(new char[] {'t','s'},new Double[]{arg,time})) * Math.cos(oFunction.value(new MathPoint(new char[] {'t','s'},new Double[]{arg,time})));
    }

    @Override
    public Double yComponent(double arg, double time) {
        return rFunction.value(new MathPoint(new char[] {'t','s'},new Double[]{arg,time})) * Math.sin(oFunction.value(new MathPoint(new char[] {'t','s'},new Double[]{arg,time})));
    }
    
}
