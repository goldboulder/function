
package Other;


public class Boundry1D {
    
    private char demension;
    private Double lowerBound;
    private Double upperBound;
    
    public Boundry1D(char demension, Double lowerBound, Double upperBound){
        this.demension = demension;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        checkBounds();
    }

    public char getDimension() {
        return demension;
    }

    public void setDemension(char demension) {
        this.demension = demension;
    }

    public Double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
        checkBounds();
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
        checkBounds();
    }
    
    public Double spaceCovered(){
        return upperBound - lowerBound;
    }
    
    public void translateBounds(double amount){
        lowerBound += amount;
        upperBound += amount;
    }
    
    private void checkBounds(){
        if (lowerBound > upperBound){
            Double temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
    }
    
    
}
