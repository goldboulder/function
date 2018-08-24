
package Graphing;

import CoreFunctions.SingleOutputFunction;
import Other.MathPoint;


public interface Parametric2DFunction extends ParametricFunction{
    
    public Double xValue(double t);
    public Double yValue(double t);
    public Double oValue(double t);
    public Double rValue(double t);
}
