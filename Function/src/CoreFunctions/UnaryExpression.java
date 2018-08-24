
package CoreFunctions;

import java.util.Set;


public abstract class UnaryExpression implements Expression{
    
    protected Expression innerFunction;

    @Override
    public boolean hasVariable(){
        return innerFunction.hasVariable();
    }
    @Override
    public Set<Character> getVariables(){
        return innerFunction.getVariables();
    }
    
}
