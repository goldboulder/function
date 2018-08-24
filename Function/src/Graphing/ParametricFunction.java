
package Graphing;

import CoreFunctions.SingleOutputFunction;
import Other.MathPoint;


public interface ParametricFunction extends GraphableFunction2D{
    public MathPoint value(double t);
    public Double derivative(char c, double t);// derivative for one function
    public void setSubFunction(char c, SingleOutputFunction f);// setSubFunction for other interfaces?
}
