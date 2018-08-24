
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class Exponentiation extends BinaryExpression{
    
    public Exponentiation(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public Double value(MathPoint p) {
        return Math.pow(leftSide.value(p),rightSide.value(p)) ;
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        if (!leftSide.hasVariable() && !rightSide.hasVariable()){//2^2
            return new Double(0);
        }
        else if (leftSide.hasVariable() && !rightSide.hasVariable()){//x^2
            return rightSide.value(p) * Math.pow(leftSide.value(p),rightSide.value(p) - 1) * leftSide.derivative(vector, p);
        }
        else if (!leftSide.hasVariable() && rightSide.hasVariable()){//2^x
            return Math.log(leftSide.value(p)) * rightSide.derivative(vector, p) * Math.pow(leftSide.value(p), rightSide.value(p));
        }
        else{//x^x
            return Math.pow(leftSide.value(p), rightSide.value(p)) * (rightSide.derivative(vector, p) * Math.log(leftSide.value(p)) + leftSide.derivative(vector, p) * rightSide.value(p) / leftSide.value(p));
        }
        
        
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        if (!leftSide.hasVariable() && !rightSide.hasVariable()){//2^2
            return new SingleOutputFunction(new Constant(0.0));
        }
        else if (leftSide.hasVariable() && !rightSide.hasVariable()){//x^2
            double rightSideValue = rightSide.value(new MathPoint(new char[]{'x'},new Double[]{0.0}));
            Expression f1 = new Exponentiation(leftSide,new Constant(rightSideValue - 1));
            Expression f2 = new Multiplication(f1,new Constant(rightSideValue));
            Expression baseFunction = new Multiplication(f2,leftSide.derivative(var));
            return new SingleOutputFunction(baseFunction);
            
        }
        else if (!leftSide.hasVariable() && rightSide.hasVariable()){//2^x
            double leftSideValue = leftSide.value(new MathPoint(new char[]{'x'},new Double[]{0.0}));
            Expression f1 = new Multiplication(this,rightSide.derivative(var));
            Expression baseFunction = new Multiplication(f1,new Constant(Math.log(leftSideValue)));
            return new SingleOutputFunction(baseFunction);
        }
        else{//x^x
            Expression f1 = new NaturalLog(leftSide);
            f1 = new Multiplication(rightSide.derivative(var),f1);
            Expression f2 = new Multiplication(leftSide.derivative(var),rightSide);
            f2 = new Division(f2,leftSide);
            Expression f3 = new Addition(f1,f2);
            Expression baseFunction = new Multiplication(this,f3);
            return new SingleOutputFunction(baseFunction);
        }
        
    }

    @Override
    public String toString() {
        return "(" + leftSide.toString() + "^" + rightSide.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
