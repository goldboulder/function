/*

 */
package MandelbrotExplorer;

import GUI.FunctionGrapherFrame;
import GUI.FunctionPanel;
import GUI.IFunctionFrame;
import Graphing.Graphable2D;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Nathan
 */
public class IterationsPanel extends JPanel implements DocumentListener{
    
    private IFunctionFrame frame;
    
    private MandelbrotGraph mSet;
    
    private JLabel mLabel;
    private JTextField mTextField;
    
    public static final int INITIAL_ITERATIONS = 1;
    
    public IterationsPanel(IFunctionFrame frame){
        this.frame = frame;
        mSet = new MandelbrotGraph(INITIAL_ITERATIONS);
        mLabel = new JLabel("Iterations");
        mTextField = new JTextField(Integer.toString(INITIAL_ITERATIONS));
        add(mLabel);
        add(mTextField);
        
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        mTextField.setMaximumSize(new Dimension(FunctionGrapherFrame.CONTROL_PANEL_WIDTH/4,FunctionPanel.TEXT_FIELD_HEIGHT));
        
        mTextField.getDocument().addDocumentListener(this);
    }
    

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateIterations();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateIterations();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateIterations();
    }
    
    private void updateIterations(){
        try{
            int i = Integer.parseInt(mTextField.getText());
            
            if (i < 1){
                mTextField.setForeground(FunctionPanel.ERROR_COLOR);
            }
            else{
                mTextField.setForeground(FunctionPanel.CORRECT_COLOR);
                mSet.setIterations(i);
                frame.repaint();
            }
        }
        catch(Exception e){
            mTextField.setForeground(FunctionPanel.ERROR_COLOR);
        }
    }

    public ArrayList<Graphable2D> getFunctions() {
        ArrayList<Graphable2D> list = new ArrayList();
        list.add(mSet);
        return list;
    }
    
}
