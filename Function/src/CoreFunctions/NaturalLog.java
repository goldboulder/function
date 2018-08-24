
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class NaturalLog extends UnaryExpression{
    
    protected NaturalLog(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.log(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return innerFunction.derivative(vector, p) / innerFunction.value(p);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression baseFunction = new Division(innerFunction.derivative(var),innerFunction);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "ln(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
