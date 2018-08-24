
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;

public class ArcCotangent extends UnaryExpression{
    
    protected ArcCotangent(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return -innerFunction.derivative(vector, p) / (1 + Math.pow(temp,2));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Exponentiation(innerFunction,new Constant(2.0));
        f1 = new Addition(f1,new Constant(1.0));
        Expression f2 = new Multiplication(new Constant(-1.0),innerFunction.derivative(var));
        f2 = new Division(f2,f1);
        return new SingleOutputFunction(f2);
    }

    @Override
    public String toString() {
        return "acot(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
