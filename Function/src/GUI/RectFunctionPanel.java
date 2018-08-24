/*

 */
package GUI;

import ConcreteFunction.PolarFunction2D;
import ConcreteFunction.RectangularFunction2D;
import Graphing.Graphable2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


class RectFunctionPanel extends FunctionPanel implements DocumentListener{
    
    private JLabel inputLabel;
    private JTextField inputTextField;
    private FunctionTemplatePanel parent;
    

    public RectFunctionPanel(FunctionTemplatePanel parent) {
        this.parent = parent;
        inputLabel = new JLabel("y(x)= ");
        inputTextField = new JTextField();
        
        add(inputLabel);
        add(inputTextField);
        
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        inputTextField.getDocument().addDocumentListener(this);
        inputTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH,TEXT_FIELD_HEIGHT));
        
        function = new RectangularFunction2D("1E308");
    }

    @Override
    public Graphable2D getFunction() {
        return function;
    }

    @Override
    public FunctionPanel getBlankCopy() {
        return new RectFunctionPanel(parent);
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.RECTANGULAR;
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
        //RectangularFunction2D rectFunction = (RectangularFunction2D) function;
        try{
            function = new RectangularFunction2D(inputTextField.getText());
            function.setColor(parent.getColor());
            inputTextField.setForeground(CORRECT_COLOR);
        }
        catch(Exception e){
            inputTextField.setForeground(ERROR_COLOR);
        }
    }

    @Override
    public Color getDefaultColor() {
        return RectangularFunction2D.DEFAULT_COLOR;
    }
    
}
