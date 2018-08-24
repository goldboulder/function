
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class Exponential extends UnaryExpression{
    
    protected Exponential(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.exp(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return innerFunction.derivative(vector, p) * value(p);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression baseFunction = new Multiplication(this,innerFunction.derivative(var));
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "eu^(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

