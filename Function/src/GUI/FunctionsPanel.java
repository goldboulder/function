/*

 */
package GUI;

import Graphing.Graphable2D;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FunctionsPanel extends JPanel{
    
    private JFrame frame;
    private LinkedList<FunctionTemplatePanel> functionTemplatePanels;
    
    public FunctionsPanel(JFrame frame){
        this.frame = frame;
        functionTemplatePanels = new LinkedList<>();
        
        functionTemplatePanels.add(new FunctionTemplatePanel(frame,this,FunctionType.RECTANGULAR));//add default panel
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        resetPanels();
    }
    
    
    public ArrayList<Graphable2D> getFunctions(){
        ArrayList<Graphable2D> functions = new ArrayList<>();
        for (FunctionTemplatePanel f : functionTemplatePanels){
            functions.add(f.getFunction());
        }
        return functions;
    }
    
    //adds a panel below the panel that sent the command
    public void addPanel(FunctionTemplatePanel caller){
        int index = functionTemplatePanels.indexOf(caller);
        FunctionTemplatePanel newPanel = new FunctionTemplatePanel(frame,this,caller.getFunctionType());
        if (!caller.colorChanged()){
            newPanel.setButtonColor(newPanel.getDefaultColor());
        }
        
        if (index == functionTemplatePanels.size() - 1){
            functionTemplatePanels.add(newPanel);
        }
        else{
            functionTemplatePanels.add(index+1,newPanel);
        }
        
        resetPanels();
        
    }
    
    public void movePanelUp(FunctionTemplatePanel caller, FunctionType type){
        int index = functionTemplatePanels.indexOf(caller);
        if (index == 0){
            return;
        }
        else{
            functionTemplatePanels.remove(caller);
            functionTemplatePanels.add(index - 1,caller);
        }
        resetPanels();
    }
    
    public void movePanelDown(FunctionTemplatePanel caller, FunctionType type){
        int index = functionTemplatePanels.indexOf(caller);
        if (index == functionTemplatePanels.size() - 1){
            return;
        }
        else{
            functionTemplatePanels.remove(caller);
            functionTemplatePanels.add(index + 1,caller);
        }
        resetPanels();
    }
    
    public void deletePanel(FunctionTemplatePanel caller){
        if (functionTemplatePanels.size() != 1){
            functionTemplatePanels.remove(caller);
            resetPanels();
        }
    }
    
    //updates the UI after altering the functionPanels
    private void resetPanels() {
        removeAll();
        for (FunctionTemplatePanel panel : functionTemplatePanels){
            add(panel);
        }
        revalidate();
        repaint();//scrollPanes do not clear previous pixels otherwise
    }
    
    
    
    
}
