/*

 */
package GUI;

import Graphing.Graphable2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class FunctionGrapherFrame extends JFrame implements ActionListener, IFunctionFrame{
    
    private GraphPanel graphPanel;
    private JPanel controlPanel;
    private TimePanel timePanel;
    private FunctionsPanel functionsPanel;
    private JScrollPane functionsScrollPane;
    private JPanel blackPanel;//separates time and functions panels
    private Timer timer;
    private static final int TIME_TICK = 30;
    private static final double dt = TIME_TICK/1000.0;
    
    public static final int GRAPH_PANEL_SIZE = 800;
    public static final int CONTROL_PANEL_WIDTH = 300;
    public static final int TIME_PANEL_HEIGHT = 50;
    
    
    public FunctionGrapherFrame(){
        setTitle("Graph Function");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphPanel = new GraphPanel(this);
        controlPanel = new JPanel();
        timePanel = new TimePanel();
        functionsPanel = new FunctionsPanel(this);
        functionsScrollPane = new JScrollPane(functionsPanel);
        blackPanel = new JPanel();
        timer = new Timer(TIME_TICK,this);
        timer.setActionCommand("timer");
        timer.start();
        //setContentPane(panel);
        
        
        controlPanel.add(timePanel);
        controlPanel.add(blackPanel);
        controlPanel.add(functionsScrollPane);
        
        add(graphPanel);
        add(controlPanel);
        
        setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
        controlPanel.add(Box.createVerticalGlue());//stops auto-vertical centering
        controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));
        
        setPreferredSize(new Dimension(GRAPH_PANEL_SIZE + CONTROL_PANEL_WIDTH,GRAPH_PANEL_SIZE));
        graphPanel.setPreferredSize(new Dimension(GRAPH_PANEL_SIZE,GRAPH_PANEL_SIZE));
        //graphPanel.setBounds(0,0,GRAPH_PANEL_SIZE,GRAPH_PANEL_SIZE);
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH,GRAPH_PANEL_SIZE));
        timePanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH,TIME_PANEL_HEIGHT));
        functionsScrollPane.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH,GRAPH_PANEL_SIZE - TIME_PANEL_HEIGHT));
        blackPanel.setMinimumSize(new Dimension(CONTROL_PANEL_WIDTH,30));
        blackPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH,30));
        blackPanel.setBackground(Color.BLACK);
        functionsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        functionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
    }
    
    @Override
    public ArrayList<Graphable2D> getFunctions(){
        return functionsPanel.getFunctions();
    }
    
    @Override
    public double getTime(){
        return timePanel.getTime();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timePanel.timeTick(dt);
        graphPanel.repaint();
    }
}
