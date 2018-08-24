/*

 */
package GUI;

import ConcreteFunction.PolarFunction2D;
import ConcreteFunction.PolarParametricFunction2D;
import ConcreteFunction.RectangularFunction2D;
import ConcreteFunction.RectangularParametricFunction2D;
import Graphing.Graphable2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;


public class FunctionTemplatePanel extends JPanel implements ActionListener{
    
    private JFrame frame;
    private FunctionsPanel parent;
    
    private JPanel optionPanel;
    private FunctionPanel functionPanel;
    private JPanel functionTypePanel;
    
    private JButton newButton;
    private JButton colorButton;
    private JButton upButton;
    private JButton downButton;
    private JButton closeButton;
    
    private JRadioButton rectRadioButton;
    private JRadioButton polRadioButton;
    private JRadioButton rectParaRadioButton;
    private JRadioButton polParaRadioButton;
    
    private ButtonGroup group;
    
    private boolean colorChanged = false;
    
    
    
    public FunctionTemplatePanel(JFrame frame, FunctionsPanel parent, FunctionType functionType){
        this.frame = frame;
        this.parent = parent;
        initiateFunctionPanel(functionType);
        setUp();
    }
    
    public FunctionTemplatePanel(JFrame frame, FunctionsPanel parent, FunctionPanel panel){
        this.frame = frame;
        this.parent = parent;
        functionPanel = panel.getBlankCopy();
        setUp();
    }
    
    private void setUp(){
        optionPanel = new JPanel();
        
        functionTypePanel = new JPanel();
        initiateButtons();
        initiateRadioButtons();
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.X_AXIS));
        functionTypePanel.setLayout(new BoxLayout(functionTypePanel,BoxLayout.X_AXIS));
        
        optionPanel.add(newButton);
        optionPanel.add(colorButton);
        optionPanel.add(upButton);
        optionPanel.add(downButton);
        optionPanel.add(closeButton);
        
        functionTypePanel.add(rectRadioButton);
        functionTypePanel.add(polRadioButton);
        functionTypePanel.add(rectParaRadioButton);
        functionTypePanel.add(polParaRadioButton);
        
        add(optionPanel);
        add(functionPanel);
        add(functionTypePanel);
        
        colorButton.setBackground(functionPanel.getColor());
        
    }

    private void initiateFunctionPanel(FunctionType functionType) {
        switch(functionType){
            case RECTANGULAR:
                functionPanel = new RectFunctionPanel(this);
            break;
            case POLAR:
                functionPanel = new PolFunctionPanel(this);
            break;
            case RECTANGULAR_PARAMETRIC:
                functionPanel = new RectParaFunctionPanel(this);
            break;
            case POLAR_PARAMETRIC: 
                functionPanel = new PolParaFunctionPanel(this);
            break;
            
        }
    }

    private void initiateButtons() {
        newButton = new JButton("New");
        colorButton = new JButton();
        upButton = new JButton("Up");
        downButton = new JButton("Down");
        closeButton = new JButton("X");
        
        newButton.addActionListener(this);
        colorButton.addActionListener(this);
        upButton.addActionListener(this);
        downButton.addActionListener(this);
        closeButton.addActionListener(this);
        
        newButton.setActionCommand("New");
        colorButton.setActionCommand("Color");
        upButton.setActionCommand("Up");
        downButton.setActionCommand("Down");
        closeButton.setActionCommand("Close");
    }

    private void initiateRadioButtons() {
        rectRadioButton = new JRadioButton("Rect",functionPanel instanceof RectFunctionPanel);
        polRadioButton = new JRadioButton("Pol",functionPanel instanceof PolFunctionPanel);
        rectParaRadioButton = new JRadioButton("RectPara",functionPanel instanceof RectParaFunctionPanel);
        polParaRadioButton = new JRadioButton("PolPara",functionPanel instanceof PolParaFunctionPanel);
        
        rectRadioButton.setActionCommand("Rect");
        polRadioButton.setActionCommand("Pol");
        rectParaRadioButton.setActionCommand("RectPara");
        polParaRadioButton.setActionCommand("PolPara");
        
        group = new ButtonGroup();
        group.add(rectRadioButton);
        group.add(polRadioButton);
        group.add(rectParaRadioButton);
        group.add(polParaRadioButton);
        
        rectRadioButton.addActionListener(this);
        polRadioButton.addActionListener(this);
        rectParaRadioButton.addActionListener(this);
        polParaRadioButton.addActionListener(this);
        
    }
    
    public Graphable2D getFunction(){
        return functionPanel.getFunction();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ((action.equals("Rect") && functionPanel instanceof RectFunctionPanel) ||
                (action.equals("Pol") && functionPanel instanceof PolFunctionPanel) ||
                (action.equals("RectPara") && functionPanel instanceof RectParaFunctionPanel) ||
                (action.equals("PolPara") && functionPanel instanceof PolParaFunctionPanel)){
            return;
        }
        
        remove(functionPanel);
        
        switch(action){
            case "New":
                parent.addPanel(this);
            break;
            case "Color":
                JDialog customDialog = new JDialog(frame, "Customize Subspace Spawner", true);//getId?
                customDialog.setLocationRelativeTo(null);

                customDialog.getContentPane().add(new JScrollPane(new ColorSelectionPanel(frame, this)));

                customDialog.pack();
                customDialog.setVisible(true);
            break;
            case "Up":
                parent.movePanelUp(this, functionPanel.getFunctionType());
            break;
            case "Down":
                parent.movePanelDown(this, functionPanel.getFunctionType());
            break;
            case "Close":
                parent.deletePanel(this);
            break;
            case "Rect":
                functionPanel = new RectFunctionPanel(this);
                if (!colorChanged){
                    functionPanel.setColor(RectangularFunction2D.DEFAULT_COLOR);
                    colorButton.setBackground(RectangularFunction2D.DEFAULT_COLOR);
                }
            break;
            case "Pol": 
                functionPanel = new PolFunctionPanel(this);
                if (!colorChanged){
                    functionPanel.setColor(PolarFunction2D.DEFAULT_COLOR);
                    colorButton.setBackground(PolarFunction2D.DEFAULT_COLOR);
                }
            break;
            case "RectPara": 
                functionPanel = new RectParaFunctionPanel(this);
                if (!colorChanged){
                    functionPanel.setColor(RectangularParametricFunction2D.DEFAULT_COLOR);
                    colorButton.setBackground(RectangularParametricFunction2D.DEFAULT_COLOR);
                }
            break;
            case "PolPara": 
                functionPanel = new PolParaFunctionPanel(this);
                if (!colorChanged){
                    functionPanel.setColor(PolarParametricFunction2D.DEFAULT_COLOR);
                    colorButton.setBackground(PolarParametricFunction2D.DEFAULT_COLOR);
                }
            break;
            default:
                
        }
        resetPanels();
    }
    
    public FunctionTemplatePanel getBlankCopy(){
        return new FunctionTemplatePanel(frame,parent,functionPanel);
    }

    public FunctionType getFunctionType() {
        return functionPanel.getFunctionType();
    }
    
    public Color getColor(){
        return colorButton.getBackground();
    }
    
    public void setColor(Color c) {
        colorButton.setBackground(c);
        functionPanel.setColor(c);
        colorChanged = true;
    }

    private void resetPanels() {
        removeAll();
        add(optionPanel);
        add(functionPanel);
        add(functionTypePanel);
        revalidate();
    }
    
    public boolean colorChanged(){
        return colorChanged;
    }

    void setButtonColor(Color color) {
        colorButton.setBackground(color);
    }

    Color getDefaultColor() {
        return functionPanel.getDefaultColor();
    }

    
    
    
}
