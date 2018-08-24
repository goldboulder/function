/*

 */
package MandelbrotExplorer;

import GUI.GraphPanel;
import GUI.IFunctionFrame;
import Graphing.Graphable2D;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class MandelbrotExplorerFrame extends JFrame implements IFunctionFrame{
    
    private GraphPanel graphPanel;
    private IterationsPanel iterationsPanel;
    
    public static final int GRAPH_PANEL_SIZE = 800;
    public static final int ITERATION_PANEL_WIDTH = 150;
    
    public MandelbrotExplorerFrame(){
        graphPanel = new GraphPanel(this);
        iterationsPanel = new IterationsPanel(this);
        
        add(graphPanel);
        add(iterationsPanel);
        
        setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
        graphPanel.setPreferredSize(new Dimension(GRAPH_PANEL_SIZE,GRAPH_PANEL_SIZE));
        iterationsPanel.setPreferredSize(new Dimension(ITERATION_PANEL_WIDTH,GRAPH_PANEL_SIZE));
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public ArrayList<Graphable2D> getFunctions() {
        return iterationsPanel.getFunctions();
    }

    @Override
    public double getTime() {
        return 0;
    }
    
}
