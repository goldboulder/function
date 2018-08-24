
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;
import java.util.Set;


public interface Expression extends MathFunction{

    public Double value(MathPoint p);
    public Double derivative(MathVector vector, MathPoint p);
    public SingleOutputFunction derivative(char var);
    public Expression integral();
    public boolean hasVariable();
    public Set<Character> getVariables();
    
    
}
