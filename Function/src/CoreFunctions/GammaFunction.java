
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;
import other.MyMath;


public class GammaFunction extends UnaryExpression{
    
    protected GammaFunction(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        double x = innerFunction.value(p);
        return MyMath.gamma(x);
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "gamma(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
