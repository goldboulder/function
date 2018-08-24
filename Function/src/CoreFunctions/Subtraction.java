
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;


public class Subtraction extends BinaryExpression{
    protected Subtraction(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public Double value(MathPoint p) {
        return leftSide.value(p) - rightSide.value(p);
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return leftSide.derivative(vector, p) - rightSide.derivative(vector, p);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression baseFunction = new Subtraction(leftSide.derivative(var),rightSide.derivative(var));
        return new SingleOutputFunction(baseFunction);
    }
    
    @Override
    public String toString() {
        return leftSide.toString() + "-" + rightSide.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
