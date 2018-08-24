/*

 */
package GUI;

import ConcreteFunction.PolarFunction2D;
import ConcreteFunction.PolarParametricFunction2D;
import ConcreteFunction.RectangularFunction2D;
import CoreFunctions.SingleOutputFunction;
import static GUI.FunctionPanel.TEXT_FIELD_HEIGHT;
import Graphing.Graphable2D;
import Other.Resolution1D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


class PolFunctionPanel extends FunctionPanel implements DocumentListener{

    private JLabel rInputLabel;
    private JTextField rInputTextField;
    private JLabel resStartLabel;
    private JLabel resEndLabel;
    private JLabel resStepsLabel;
    private JTextField resStartTextField;
    private JTextField resEndTextField;
    private JTextField resStepsTextField;
    private JPanel rInputPanel;
    private JPanel resPanel;
    
    private FunctionTemplatePanel parent;

    public PolFunctionPanel(FunctionTemplatePanel parent) {
        this.parent = parent;
        
        rInputLabel = new JLabel("r(o)= ");
        rInputTextField = new JTextField();
        rInputPanel = new JPanel();
        resStartLabel = new JLabel("t min");
        resEndLabel = new JLabel("t max");
        resStepsLabel = new JLabel("steps");
        resStartTextField = new JTextField();
        resEndTextField = new JTextField();
        resStepsTextField = new JTextField();
        resPanel = new JPanel();
        
        rInputPanel.setLayout(new BoxLayout(rInputPanel,BoxLayout.X_AXIS));
        resPanel.setLayout(new BoxLayout(resPanel,BoxLayout.X_AXIS));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        
        rInputPanel.add(rInputLabel);
        rInputPanel.add(rInputTextField);
        resPanel.add(resStartLabel);
        resPanel.add(resStartTextField);
        resPanel.add(resEndLabel);
        resPanel.add(resEndTextField);
        resPanel.add(resStepsLabel);
        resPanel.add(resStepsTextField);
        
        add(rInputPanel);
        add(resPanel);
        
        rInputTextField.getDocument().addDocumentListener(this);
        resStartTextField.getDocument().addDocumentListener(this);
        resEndTextField.getDocument().addDocumentListener(this);
        resStepsTextField.getDocument().addDocumentListener(this);
        
        rInputTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH,TEXT_FIELD_HEIGHT));
        resStartTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,TEXT_FIELD_HEIGHT));
        resEndTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,TEXT_FIELD_HEIGHT));
        resStepsTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,TEXT_FIELD_HEIGHT));
        
        function = new PolarFunction2D("0");
        
    }

    @Override
    public Graphable2D getFunction() {
        return function;
    }

    @Override
    public FunctionPanel getBlankCopy() {
        return new PolFunctionPanel(parent);
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.POLAR;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateFunction();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateFunction();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateFunction();
    }
    
    private void updateFunction(){
        try{
            function = new PolarFunction2D(rInputTextField.getText());
            function.setColor(parent.getColor());
            rInputTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            rInputTextField.setForeground(ERROR_COLOR);
        }
        PolarFunction2D polFunction = (PolarFunction2D) function;
        try{
            double num = Double.parseDouble(resStartTextField.getText());
            
            polFunction.setStart(num);
            resStartTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            resStartTextField.setForeground(ERROR_COLOR);
        }
        
        try{
            double num = Double.parseDouble(resEndTextField.getText());
            polFunction.setEnd(num);
            resEndTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            resEndTextField.setForeground(ERROR_COLOR);
        }
        
        try{
            int num = Integer.parseInt(resStepsTextField.getText());
            if(num < Resolution1D.MIN_STEPS || num > 10000){
                throw new Exception();
            }
            polFunction.setSteps(num);
            resStepsTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            resStepsTextField.setForeground(ERROR_COLOR);
        }
        
    }

    @Override
    public Color getDefaultColor() {
        return PolarFunction2D.DEFAULT_COLOR;
    }
    
}
