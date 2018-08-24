
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;
import other.MyMath;


public class Tetration extends BinaryExpression{
    
    protected Tetration(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public Double value(MathPoint p) {
        return MyMath.tetra(leftSide.value(p), rightSide.value(p));
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return leftSide.toString() + "`" + rightSide.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

