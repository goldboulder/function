
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;

public class Cosecant extends UnaryExpression{
    
    protected Cosecant(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return -innerFunction.derivative(vector, p) / (Math.tan(temp) * Math.sin(temp));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Multiplication(new Constant(-1.0),innerFunction.derivative(var));
        Expression f2 = new Tangent(innerFunction);
        Expression f3 = new Sine(innerFunction);
        Expression f4 = new Multiplication(f2,f3);
        Expression baseFunction = new Division(f1,f4);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "csc(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
