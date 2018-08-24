
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class CommonLog extends UnaryExpression{
    
    protected CommonLog(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.log10(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return innerFunction.derivative(vector, p) / (Math.log(10) * innerFunction.value(p));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Division(innerFunction.derivative(var),innerFunction);
        Expression baseFunction = new Division(f1,new Constant(Math.log(10)));
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return "log(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

