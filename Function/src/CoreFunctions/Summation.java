
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import java.util.Set;


public class Summation extends Series{

    
    public Summation(SingleOutputFunction function, int start, int end, char variable){
        if (start > end){
            throw new RuntimeException("Invalid arguments: start must be <= end");
        }
        this.function = function;
        this.start = start;
        this.end = end;
        this.variable = variable;
    }
    
    public Summation(String str, int start, int end, char variable){
        
        SingleOutputFunction temp = new SingleOutputFunction(str);
        this.function = temp.getExpression();
        this.start = start;
        this.end = end;
        this.variable = variable;
    }
    
    
    
    @Override
    public Double value(MathPoint p) {// add summation character to a clone of the math point?@@@@@@@@@@@@@@@
        MathPoint newPoint = new MathPoint(p);
        newPoint.addPoint(variable, (double)getStart());
        
        Double sum = 0.0;
        for (double i = getStart(); i <= getEnd(); i++){
            newPoint.setPosition(variable, i);
            sum += function.value(newPoint);
        }
        return sum;
    }
    
    // does the series converge? What value does it converge to? Infinity or NaN if doesn't converge
    @Override
    public Double limit(MathPoint p){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double derivative(MathVector vector, MathPoint p) {
        
        MathPoint newPoint = new MathPoint(p);
        newPoint.addPoint(variable, (double)getStart());
        
        Double sum = 0.0;
        for (double i = getStart(); i <= getEnd(); i++){
            newPoint.setPosition(variable, i);
            sum += function.derivative(vector,newPoint);
        }
        return sum;
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        Expression baseFunction = new Summation(function.derivative(var), getStart(), getEnd(),variable);
        return new SingleOutputFunction(baseFunction);
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    @Override
    public String toString() {
        return "sum(" + function + "," + getStart() + "," + getEnd() + "," + variable + ")";
    }
    
}
