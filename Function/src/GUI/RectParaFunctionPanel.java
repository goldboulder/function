/*

 */
package GUI;

import ConcreteFunction.RectangularParametricFunction2D;
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


class RectParaFunctionPanel extends FunctionPanel implements DocumentListener{

    private JLabel xInputLabel;
    private JTextField xInputTextField;
    private JLabel yInputLabel;
    private JTextField yInputTextField;
    private JLabel resStartLabel;
    private JLabel resEndLabel;
    private JLabel resStepsLabel;
    private JTextField resStartTextField;
    private JTextField resEndTextField;
    private JTextField resStepsTextField;
    private JPanel xInputPanel;
    private JPanel yInputPanel;
    private JPanel resPanel;
    
    private FunctionTemplatePanel parent;

    public RectParaFunctionPanel(FunctionTemplatePanel parent) {
        this.parent = parent;
        
        xInputLabel = new JLabel("x(t)= ");
        xInputTextField = new JTextField();
        yInputLabel = new JLabel("y(t)= ");
        yInputTextField = new JTextField();
        xInputPanel = new JPanel();
        yInputPanel = new JPanel();
        resStartLabel = new JLabel("t min");
        resEndLabel = new JLabel("t max");
        resStepsLabel = new JLabel("steps");
        resStartTextField = new JTextField();
        resEndTextField = new JTextField();
        resStepsTextField = new JTextField();
        resPanel = new JPanel();
        
        xInputPanel.setLayout(new BoxLayout(xInputPanel,BoxLayout.X_AXIS));
        yInputPanel.setLayout(new BoxLayout(yInputPanel,BoxLayout.X_AXIS));
        resPanel.setLayout(new BoxLayout(resPanel,BoxLayout.X_AXIS));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        xInputPanel.add(xInputLabel);
        xInputPanel.add(xInputTextField);
        yInputPanel.add(yInputLabel);
        yInputPanel.add(yInputTextField);
        resPanel.add(resStartLabel);
        resPanel.add(resStartTextField);
        resPanel.add(resEndLabel);
        resPanel.add(resEndTextField);
        resPanel.add(resStepsLabel);
        resPanel.add(resStepsTextField);
        
        add(xInputPanel);
        add(yInputPanel);
        add(resPanel);
        
        xInputTextField.getDocument().addDocumentListener(this);
        yInputTextField.getDocument().addDocumentListener(this);
        resStartTextField.getDocument().addDocumentListener(this);
        resEndTextField.getDocument().addDocumentListener(this);
        resStepsTextField.getDocument().addDocumentListener(this);
        
        xInputTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH,TEXT_FIELD_HEIGHT));
        yInputTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH,TEXT_FIELD_HEIGHT));
        resStartTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,TEXT_FIELD_HEIGHT));
        resEndTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,TEXT_FIELD_HEIGHT));
        resStepsTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,TEXT_FIELD_HEIGHT));
        
        function = new RectangularParametricFunction2D("0","0");
        
    }

    @Override
    public Graphable2D getFunction() {
        return function;
    }

    @Override
    public FunctionPanel getBlankCopy() {
        return new RectParaFunctionPanel(parent);
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.RECTANGULAR_PARAMETRIC;
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
        RectangularParametricFunction2D rectParaFunction = (RectangularParametricFunction2D) function;
        try{
            rectParaFunction.setSubFunction('x', new SingleOutputFunction(xInputTextField.getText()));
            xInputTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            xInputTextField.setForeground(ERROR_COLOR);
        }
        
        try{
            rectParaFunction.setSubFunction('y', new SingleOutputFunction(yInputTextField.getText()));
            yInputTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            yInputTextField.setForeground(ERROR_COLOR);
        }
        
        try{
            double num = Double.parseDouble(resStartTextField.getText());
            rectParaFunction.setStart(num);
            resStartTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            resStartTextField.setForeground(ERROR_COLOR);
        }
        
        try{
            double num = Double.parseDouble(resEndTextField.getText());
            rectParaFunction.setEnd(num);
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
            rectParaFunction.setSteps(num);
            resStepsTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            resStepsTextField.setForeground(ERROR_COLOR);
        }
        
    }

    @Override
    public Color getDefaultColor() {
        return RectangularParametricFunction2D.DEFAULT_COLOR;
    }
    
}
