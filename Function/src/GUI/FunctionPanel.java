/*

 */
package GUI;

import Graphing.Graphable2D;
import Graphing.GraphableFunction2D;
import java.awt.Color;
import javax.swing.JPanel;


public abstract class FunctionPanel extends JPanel{
    
    public static final Color CORRECT_COLOR = Color.BLACK;
    public static final Color ERROR_COLOR = Color.RED;
    public static final int TEXT_FIELD_HEIGHT = 20;
    
    protected GraphableFunction2D function;
    
    public abstract Graphable2D getFunction();
    public abstract FunctionPanel getBlankCopy();
    public abstract FunctionType getFunctionType();
    public abstract Color getDefaultColor();

    public Color getColor(){
        if (function != null){
            return function.getColor();
        }
        else{
            return getDefaultColor();
        }
    }
    
    public void setColor(Color c) {
        function.setColor(c);
    }
    
    
}
