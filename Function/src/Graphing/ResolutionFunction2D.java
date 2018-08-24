
package Graphing;


public interface ResolutionFunction2D extends ResolutionFunction{
    public double getStart();
    public double getEnd();
    public int getSteps();
    public void setStart(double start);
    public void setEnd(double end);
    public void setSteps(int steps);
}
