
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;


public class SquareRoot extends UnaryExpression{
    
    public SquareRoot(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.sqrt(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return innerFunction.derivative(vector, p) / (2 * Math.sqrt(innerFunction.value(p)));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Multiplication(this,new Constant(2.0));
        Expression baseFunction = new Division(innerFunction.derivative(var),f1);
        return new SingleOutputFunction(baseFunction);
    }
    
    @Override
    public String toString() {
        return "sqrt(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
