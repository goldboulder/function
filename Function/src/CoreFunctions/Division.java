
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class Division extends BinaryExpression{
    
    public Division(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public Double value(MathPoint p) {
        return leftSide.value(p) / rightSide.value(p);
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return (rightSide.value(p) * leftSide.derivative(vector, p) - leftSide.value(p) * rightSide.derivative(vector, p)) / (rightSide.value(p) * rightSide.value(p));
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression f1 = new Multiplication(rightSide,leftSide.derivative(var));
        Expression f2 = new Multiplication(leftSide,rightSide.derivative(var));
        Expression f3 = new Subtraction(f1,f2);
        Expression f4 = new Multiplication(rightSide,rightSide);
        Expression baseFunction = new Division(f3,f4);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public String toString() {
        return leftSide.toString() + "/" + rightSide.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
