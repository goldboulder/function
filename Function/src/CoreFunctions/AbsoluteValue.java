
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class AbsoluteValue extends UnaryExpression{
    
    protected AbsoluteValue(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.abs(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        if (temp > 0)
            return innerFunction.derivative(vector, p);
        else if (temp < 0)
            return -innerFunction.derivative(vector, p);
        else
            return Double.NaN;
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Sign(innerFunction);
        Expression baseFunction = new Multiplication(innerFunction.derivative(var),f1);
        return new SingleOutputFunction(baseFunction);
    }

    


    @Override
    public String toString() {
        return "|" + innerFunction.toString() + "|";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
