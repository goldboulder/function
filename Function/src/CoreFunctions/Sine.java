
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;


public class Sine extends UnaryExpression{
    
    protected Sine(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.sin(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return Math.cos(innerFunction.value(p)) * innerFunction.derivative(vector, p);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression baseFunction = new Multiplication(new Cosine(innerFunction),innerFunction.derivative(var));
        return new SingleOutputFunction(baseFunction);
    }
    
    @Override
    public String toString() {
        return "sin(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
