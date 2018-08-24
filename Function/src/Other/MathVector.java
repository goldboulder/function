
package Other;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class MathVector implements Cloneable, Serializable{
    private HashMap<Character,Double> map;
    private ArrayList<Character> dimensionList;
    
    public MathVector(char[] dimensionNames, Double[] coordinates){
        dimensionList = new ArrayList<>();
        if (dimensionNames.length != coordinates.length)
            throw new RuntimeException("The length of the lists must be the same");
        if (coordinates.length == 0)
            throw new RuntimeException("The length of the lists must be at least 1");
        
    
        map = new HashMap<>();
        
        for (int i = 0; i < coordinates.length; i++){
            map.put(dimensionNames[i], coordinates[i]);
            dimensionList.add(dimensionNames[i]);
        }
    }
    
    public int dimensions(){
        return map.size();
    }
    
    
    public double getPosition(char dimension){
        return map.getOrDefault(dimension,0.0);
    }
    
    
    public void setPosition(char dimension, Double position){
        map.replace(dimension, position);
    }
    
    public int getNumDimensions(){
        return map.size();
    }
    
    public boolean hasDimension(Character c){
        if (dimensionList.contains(c))
            return true;
        return false;
    }
    
    public char[] getDimensionNames(){
        char[] ans = new char[dimensionList.size()];
        for (int i = 0; i < dimensionList.size(); i++){
            ans[i] = dimensionList.get(i);
        }
        return ans;
    }
    
    /*
    public double length(){
        double sumOfSquares = 0;
        for (Double parameter : parameters) {
            sumOfSquares += parameter * parameter;
        }
        return Math.sqrt(sumOfSquares); 
    }
    
    public void normalize(){
        double vectorLength = length();
        for (Double parameter : parameters) {
            parameter /= vectorLength;
        }
    }
*/
    
    
}
