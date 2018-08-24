
package Other;


public class Resolution1D {
    private char dimension;
    private double min;
    private double max;
    private int steps;
    public static final int MIN_STEPS = 2;


    public Resolution1D(char dimension, double min, double max, int steps){
        this.dimension = dimension;
        this.min = min;
        this.max = max;
        if (steps >= MIN_STEPS){
            this.steps = steps;
        }
        else{
            this.steps = MIN_STEPS;
        }
        
    }
    
    public char getDimension() {
        return dimension;
    }


    public void setDimension(char dimension) {
        this.dimension = dimension;
    }

    public double getMin() {
        return min;
    }


    public void setMin(double min) {
        this.min = min;
        checkBounds();
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
        checkBounds();
    }


    public int getSteps() {
        return steps;
    }


    public void setSteps(int steps) {
        if (steps >= MIN_STEPS){
            this.steps = steps;
        }
        else{
            this.steps = MIN_STEPS;
        }
    }
    
    public double stepLength(){
        return (max - min) / (steps - 1);
    }

    private void checkBounds() {
        if (min > max){
            double temp = min;
            min = max;
            max = temp;
        }
    }
    
    
}
