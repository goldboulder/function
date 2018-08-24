
package CoreFunctions;

//sumation and its multiplication equivilent

import Other.MathPoint;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Series implements MultiParamExpression{
    
    protected Expression function;
    public int start; // inclusive
    public int end; // inclusive
    protected char variable;
    
    public abstract Double limit(MathPoint p);
    
    public int numTerms() {
        if (start > end){
            return 0;
        }
        return  1 + end - start;
    }
    
    public SingleOutputFunction getFunction(){
        return new SingleOutputFunction(function);
    }
    
    public int getStart() {
        return start;
    }

    
    public void setStart(int start) {
        this.start = start;
    }

    
    public int getEnd() {
        return end;
    }

    
    public void setEnd(int end) {
        this.end = end;
    }
    
    @Override
    public boolean hasVariable(){
        return function.hasVariable();
    }
    
    @Override
    public Set<Character> getVariables(){
        return function.getVariables();
    }

    

    
}
