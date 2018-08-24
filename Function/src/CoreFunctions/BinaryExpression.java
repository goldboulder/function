
package CoreFunctions;

import java.util.LinkedHashSet;
import java.util.Set;


public abstract class BinaryExpression implements Expression{

    protected Expression leftSide;
    protected Expression rightSide;
    
    @Override
    public boolean hasVariable(){
        return leftSide.hasVariable() || rightSide.hasVariable();
    }
    
    @Override
    public Set<Character> getVariables(){
        Set<Character> list = new LinkedHashSet<>();
        list.addAll(leftSide.getVariables());
        list.addAll(rightSide.getVariables());
        return list;
    }
    
}
