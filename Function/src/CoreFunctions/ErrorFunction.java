
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class ErrorFunction extends UnaryExpression{
    
    protected ErrorFunction(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return innerFunction.derivative(vector, p) * (2 / Math.sqrt(Math.PI)) * Math.exp(-temp*temp);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Exponentiation(innerFunction, new Constant(2.0));
        f1 = new Multiplication(f1,new Constant(-1.0));
        f1 = new Exponential(f1);
        f1 = new Multiplication(f1,innerFunction.derivative(var));
        f1 = new Multiplication(f1,new Constant(2/Math.sqrt(Math.PI)));
        return new SingleOutputFunction(f1);
    }

    @Override
    public String toString() {
        return "erf(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

