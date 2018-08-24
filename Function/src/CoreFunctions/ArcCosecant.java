
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class ArcCosecant extends UnaryExpression{
    
    protected ArcCosecant(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return -innerFunction.derivative(vector, p) / (Math.abs(temp)*Math.sqrt(temp*temp - 1));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Exponentiation(innerFunction,new Constant(2.0));
        f1 = new Subtraction(f1,new Constant(1.0));
        f1 = new SquareRoot(f1);
        Expression f2 = new AbsoluteValue(innerFunction);
        Expression f3 = new Multiplication(f1,f2);
        f3 = new Division(innerFunction.derivative(var),f3);
        f3 = new Multiplication(new Constant(-1.0),f3);
        return new SingleOutputFunction(f3);
    }

    


    @Override
    public String toString() {
        return "acsc(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

