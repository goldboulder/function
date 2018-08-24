
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class Cotangent extends UnaryExpression{
    
    protected Cotangent(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return -innerFunction.derivative(vector, p) / Math.pow(Math.sin(temp),2);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Multiplication(new Constant(-1.0),innerFunction.derivative(var));
        Expression f2 = new Sine(innerFunction);
        f2 = new Exponentiation(f2,new Constant(2.0));
        Expression baseFunction = new Division(f1,f2);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "cot(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

