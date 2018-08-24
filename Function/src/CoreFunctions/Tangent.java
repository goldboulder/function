
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;


public class Tangent extends UnaryExpression{
    
    protected Tangent(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.tan(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return innerFunction.derivative(vector, p) / Math.pow(Math.cos(innerFunction.value(p)), 2);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Cosine(innerFunction);
        Expression f2 = new Exponentiation(f1,new Constant(2.0));
        Expression baseFunction = new Division(innerFunction.derivative(var),f2);
        return new SingleOutputFunction(baseFunction);
    }
    
    @Override
    public String toString() {
        return "tan(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
