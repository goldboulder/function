/*

 */
package GUI;

import Graphing.Graphable2D;
import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public interface IFunctionFrame {
    ArrayList<Graphable2D> getFunctions();
    double getTime();
    void repaint();
}
