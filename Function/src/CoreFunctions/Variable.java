
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;
import java.util.LinkedHashSet;
import java.util.Set;


public class Variable extends SimpleExpression{
    private Character letter;
    
    protected Variable(Character letter){
        this.letter = letter;
    }

    @Override
    public Double value(MathPoint p) {
        return p.getPosition(letter);
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        return vector.getPosition(letter);
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        if (letter.charValue() == var){
            return new SingleOutputFunction(new Constant(1.0));
        }
        else{
            return new SingleOutputFunction(new Constant(0.0));
        }
    }
    
    @Override
    public String toString() {
        return letter.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasVariable() {
        return true;
    }

    @Override
    public Set<Character> getVariables() {
        Set<Character> s = new LinkedHashSet();
        s.add(letter);
        return s;
    }
    
}
