
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;


public class Secant extends UnaryExpression{
    
    protected Secant(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return innerFunction.derivative(vector, p) * Math.tan(temp) / Math.cos(temp);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Tangent(innerFunction);
        f1 = new Multiplication(innerFunction.derivative(var),f1);
        Expression f2 = new Cosine(innerFunction);
        Expression baseFunction = new Division(f1,f2);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "sec(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

