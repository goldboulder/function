
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class Cosine extends UnaryExpression{
    
    protected Cosine(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.cos(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return -Math.sin(innerFunction.value(p) * innerFunction.derivative(vector, p));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Multiplication(new Sine(innerFunction),innerFunction.derivative(var));
        Expression baseFunction = new Multiplication(new Constant(-1.0),f1);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "cos(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
