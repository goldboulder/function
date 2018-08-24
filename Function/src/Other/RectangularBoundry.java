
package Other;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class RectangularBoundry {
    private HashMap<Character,Boundry1D> boundries;
    
    public RectangularBoundry(Boundry1D... boundries){
        this.boundries = new LinkedHashMap<>();
        for (Boundry1D boundry : boundries){
            checkDuplicateDemensions(boundry);
            this.boundries.put(boundry.getDimension(),boundry);
        }
    }
    
    public RectangularBoundry(List<Boundry1D> list){
        this.boundries = new LinkedHashMap<>();
        for (Boundry1D boundry : list){
            checkDuplicateDemensions(boundry);
            this.boundries.put(boundry.getDimension(),boundry);
        }
    }
    
    public int dimensions(){
        return boundries.size();
    }
    
    public Boundry1D getBoundry(Character c){
        Boundry1D ans = boundries.get(c);
        if (ans != null){
            return ans;
        }
        throw new RuntimeException("Boundry dimension not found: " + c);
    }
    
    public void setLowerBound(Character c, Double bound){
        Boundry1D b = boundries.get(c);
        if (b != null){
            b.setLowerBound(bound);
        }
        else{
            throw new RuntimeException("Boundry dimension not found: " + c);
        }
    }
    
    public void setUpperBound(Character c, Double bound){
        Boundry1D b = boundries.get(c);
        if (b != null){
            b.setUpperBound(bound);
        }
        else{
            throw new RuntimeException("Boundry dimension not found: " + c);
        }
    }
    
    public double spaceEnclosed(){//deletes boundries***
        double product = 1;
        Iterator it = boundries.entrySet().iterator();
        Boundry1D b;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            b = (Boundry1D)pair.getValue();
            product *= b.spaceCovered();
            it.remove(); // avoids a ConcurrentModificationException, removes boundry?
        }
        
        
        //for (Boundry1D boundry : boundries){
            //product *= boundry.spaceCovered();
        //}
        return product;
    }
    
    private void checkDuplicateDemensions(Boundry1D boundry){
        Boundry1D b = boundries.get(boundry.getDimension());
        if (b == null){
            return;
        }
        else{
            throw new RuntimeException("Duplicate boundry demensions");
        }
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        Iterator it = boundries.entrySet().iterator();
        Boundry1D b;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            b = (Boundry1D)pair.getValue();
            s.append(b.getDimension() + ":  " + b.getLowerBound() + "  " + b.getUpperBound() + "  ,   ");
            //s.append(b.getUpperBound() - b.getLowerBound() + "   ");
            //it.remove(); // avoids a ConcurrentModificationException
        }
        return s.toString();
    }
    
}
