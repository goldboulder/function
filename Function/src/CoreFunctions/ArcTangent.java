
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;

public class ArcTangent extends UnaryExpression{
    
    protected ArcTangent(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.atan(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return innerFunction.derivative(vector, p) / (1 + temp * temp);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Exponentiation(innerFunction,new Constant(2.0));
        f1 = new Addition(new Constant(1.0),f1);
        f1 = new Division(innerFunction.derivative(var),f1);
        return new SingleOutputFunction(f1);
    }

    @Override
    public String toString() {
        return "atan(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

