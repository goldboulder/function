
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class RandomFunction extends UnaryExpression{
    
    protected RandomFunction(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return Double.NaN;
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "rand(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

