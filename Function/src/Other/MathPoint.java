
package Other;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MathPoint implements Cloneable, Serializable{
    private HashMap<Character,Double> map;
    private List<Character> dimensionList;
    
    public MathPoint(){
        dimensionList = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public MathPoint(char[] dimensionNames, Double[] coordinates){
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
    
    public MathPoint(MathPoint p){
        dimensionList = new ArrayList<>();
        map = new HashMap<>();
        
        for (int i = 0; i < p.dimensionList.size(); i++){
            map.put(p.dimensionList.get(i), p.map.get(p.dimensionList.get(i)));
            dimensionList.add(p.dimensionList.get(i));
        }
    }
    
    public void addPoint(char var, Double value){
        map.put(var, value);
        dimensionList.add(var);
    }
    
    public void deletePoint(char var){
        map.remove(var);
        dimensionList.remove(new Character(var));
    }
    
    public Double getPosition(char dimension){
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
    
    public List<Character> getDimensionList(){
        return dimensionList;
    }
    
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder("");
        for (Character c : map.keySet()){
            b.append(c + ": " + map.get(c) + "  ");
        }
        return b.toString();
    }
    
}
