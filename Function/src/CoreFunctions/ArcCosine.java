
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;

public class ArcCosine extends UnaryExpression{
    
    protected ArcCosine(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.acos(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return -innerFunction.derivative(vector, p) / Math.sqrt(1-temp*temp);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Exponentiation(innerFunction,new Constant(2.0));
        f1 = new Subtraction(new Constant(1.0),f1);
        f1 = new SquareRoot(f1);
        f1 = new Division(innerFunction.derivative(var),f1);
        f1 = new Multiplication(new Constant(-1.0),f1);
        return new SingleOutputFunction(f1);
    }

    @Override
    public String toString() {
        return "acos(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

