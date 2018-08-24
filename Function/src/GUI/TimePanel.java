/*

 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TimePanel extends JPanel implements ActionListener{
    
    private JLabel timeLabel;
    private JPanel timePanel;
    private JPanel timeControlPanel;
    
    private JButton rewindButton;
    private JButton pauseButton;
    private JButton passForwardButton;
    private JButton resetButton;
    
    private double time = 0;
    private double timeSpeed = 0;//starts paused
    
    private DecimalFormat df = new DecimalFormat("###,###.00");
    
    
    public TimePanel(){
        timePanel = new JPanel();
        timeLabel = new JLabel("Time: 0");
        timeControlPanel = new JPanel();
        
        rewindButton = new JButton("<<");
        pauseButton = new JButton("||");
        passForwardButton = new JButton(">>");
        resetButton = new JButton("Reset");
        
        rewindButton = new JButton("<<");
        pauseButton = new JButton("||");
        passForwardButton = new JButton(">>");
        resetButton = new JButton("Reset");
        
        rewindButton.setActionCommand("rewind");
        pauseButton.setActionCommand("pause");
        passForwardButton.setActionCommand("pass forward");
        resetButton.setActionCommand("reset");
        
        rewindButton.addActionListener(this);
        pauseButton.addActionListener(this);
        passForwardButton.addActionListener(this);
        resetButton.addActionListener(this);
        
        
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        timePanel.setPreferredSize(new Dimension(100,30));
        //timePanel.setBackground(Color.WHITE);
        timeControlPanel.setLayout(new BoxLayout(timeControlPanel,BoxLayout.X_AXIS));
        
        timePanel.add(timeLabel);
        
        timeControlPanel.add(rewindButton);
        timeControlPanel.add(pauseButton);
        timeControlPanel.add(passForwardButton);
        timeControlPanel.add(resetButton);
        
        add(timePanel);
        add(timeControlPanel);
        
    }
    
    public double getTime(){
        return time;
    }
    
    public void timeTick(double timeIncrement){
        time += timeIncrement*timeSpeed;
        if (time == 0){
            timeLabel.setText("Time:  " + 0);
        }
        else{
            timeLabel.setText("Time:  " + df.format(time));
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch(e.getActionCommand()){
            case "rewind":
                if (timeSpeed > -1){
                    timeSpeed = -1;
                }
                else{
                    timeSpeed --;
                }
                
            break;
            case "pause":
                timeSpeed = 0;
            break;
            case "pass forward":
                if (timeSpeed < 1){
                    timeSpeed = 1;
                }
                else{
                    timeSpeed ++;
                }
            break;
            case "reset":
                time = 0;
            break;
            default:
                //nothing
        }
    }
    
}
