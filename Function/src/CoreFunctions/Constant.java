
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import Other.Boundry1D;
import java.util.LinkedHashSet;
import java.util.Set;


public class Constant extends SimpleExpression{
    private Double constant;
    
    public Constant(Double constant){
        this.constant = constant;
    }

    @Override
    public Double value(MathPoint p) {
        return constant;
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return 0.0;
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        return new SingleOutputFunction(new Constant(0.0));
    }

    @Override
    public String toString() {
        return constant.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasVariable() {
        return false;
    }

    @Override
    public Set<Character> getVariables() {
        Set<Character> s = new LinkedHashSet();
        return s;
    }
    
}
