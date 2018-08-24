
package Graphing;

import Other.UsefulThings;
import java.awt.Color;


public abstract class GridPainter2D implements Graphable2D{
    protected Color minorGridColor = new Color(0,0,0,25);
    protected Color majorGridColor = new Color(0,0,0,80);
    protected Color tickColor = Color.BLACK;
    protected Color fontColor = Color.BLACK;
    
    public void setMinorGridColor(Color minorGridColor) {
        this.minorGridColor = minorGridColor;
    }

    
    public void setMajorGridColor(Color majorGridColor) {
        this.majorGridColor = majorGridColor;
    }

    
    public void setTickColor(Color tickColor) {
        this.tickColor = tickColor;
    }
    
    protected Color colorForMarking(double mark, double scale){
        if (UsefulThings.isMilestone(mark,scale,5)){
            if (Math.abs(mark/scale) > 0.01){
                return majorGridColor;
            }
            else{
                return tickColor;
            }
        }
        else{
            return minorGridColor;
        }
    }
}
