
package CoreFunctions;

import Other.MathPoint;
import Other.MathVector;
import java.util.Set;


public class Product extends Series{

    public Product(SingleOutputFunction function, int start, int end, char variable){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Product(String str, int start, int end, char variable){
        
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
        
        Double product = 1.0;
        for (double i = getStart(); i <= getEnd(); i++){
            newPoint.setPosition(variable, i);
            product *= function.value(newPoint);
        }
        return product;
    }
    
    // does the series converge? What value does it converge to? Infinity or NaN if doesn't converge
    @Override
    public Double limit(MathPoint p){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    @Override
    public String toString() {
        return "prod(" + function + "," + getStart() + "," + getEnd() + "," + variable + ")";
    }
    
}
