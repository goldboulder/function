
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;
import other.MyMath;


public class SuperRoot extends UnaryExpression{
    
    protected SuperRoot(Expression innerFunction){
        this.innerFunction = innerFunction;
    }

    @Override
    public Double value(MathPoint p) {
        return MyMath.sprt(innerFunction.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return innerFunction.derivative(vector, p) / (innerFunction.value(p) * (1 + Math.log(MyMath.sprt(innerFunction.value(p)))));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new NaturalLog(this);
        f1 = new Addition(f1,new Constant(1.0));
        f1 = new Multiplication(innerFunction,f1);
        f1 = new Division(innerFunction.derivative(var),f1);
        return new SingleOutputFunction(f1);
    }
    
    @Override
    public String toString() {
        return "sprt(" + innerFunction.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

