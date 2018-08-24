
package Graphing;

import Other.Boundry1D;
import Other.RectangularBoundry;


public interface NonParametric2DFunction extends GraphableFunction2D{
    public Double value(double x);
    public Double derivative(double x);
    public Double integral(double lowerBound, double upperBound);
}
