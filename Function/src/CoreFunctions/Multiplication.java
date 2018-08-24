
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;

public class Multiplication extends BinaryExpression{
    
    public Multiplication(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public Double value(MathPoint p) {
        //System.out.println(leftSide + "    " + rightSide);
        return leftSide.value(p) * rightSide.value(p);
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return leftSide.value(p) * rightSide.derivative(vector, p) + leftSide.derivative(vector,p) * rightSide.value(p);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        if (!leftSide.hasVariable() && !rightSide.hasVariable()){//2*2
            return new SingleOutputFunction(new Constant(0.0));
        }
        else if (leftSide.hasVariable() && !rightSide.hasVariable()){//x*2
            double rightSideValue = rightSide.value(new MathPoint(new char[]{'x'},new Double[]{0.0}));// rightSide is a constant
            Expression baseFunction = new Multiplication(new Constant(rightSideValue),leftSide.derivative(var));
            return new SingleOutputFunction(baseFunction);
            
        }
        else if (!leftSide.hasVariable() && rightSide.hasVariable()){//2*x
            double leftSideValue = leftSide.value(new MathPoint(new char[]{'x'},new Double[]{0.0}));
            Expression baseFunction = new Multiplication(new Constant(leftSideValue),rightSide.derivative(var));
            return new SingleOutputFunction(baseFunction);
        }
        else{//x*x
            Expression b1 = new Multiplication(leftSide,rightSide.derivative(var));
            Expression b2 = new Multiplication(rightSide,leftSide.derivative(var));
            Expression baseFunction = new Addition(b1,b2);
            return new SingleOutputFunction(baseFunction);
        }
        
    }

    @Override
    public String toString() {
        return "(" + leftSide.toString() + "*" + rightSide.toString() + ")";
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
