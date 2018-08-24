
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;

public class HyperbolicTangent extends UnaryExpression{
    
    protected HyperbolicTangent(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.tanh(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        double temp = innerFunction.value(p);
        return innerFunction.derivative(vector, p) / Math.pow(Math.cosh(temp),2);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new HyperbolicCosine(innerFunction);
        f1 = new Exponentiation(f1,new Constant(2.0));
        f1 = new Division(innerFunction.derivative(var),f1);
        return new SingleOutputFunction(f1);
    }

    @Override
    public String toString() {
        return "tanh(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}