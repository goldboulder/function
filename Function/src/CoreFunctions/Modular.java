
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;


public class Modular extends BinaryExpression{
    
    protected Modular(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public Double value(MathPoint p) {
        return leftSide.value(p) % rightSide.value(p);
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*
        double ans = leftSide.value(p) % rightSide.value(p);
        if (ans != 0){
            return ans;
        }
        else{
            return Double.NaN;
        }
        */
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return leftSide.toString() + "%" + rightSide.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
