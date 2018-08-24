/*

 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ColorSelectionPanel extends JPanel implements ActionListener{
    
    private JFrame frame;
    private FunctionTemplatePanel parent;
    
    private JButton redButton;
    private JButton orangeButton;
    private JButton greenButton;
    private JButton blueButton;
    private JButton purpleButton;
    private JButton blackButton;
    private JButton brownButton;
    private JButton grayButton;
    private JButton pinkButton;
    private JButton limeButton;
    private JButton lightBlueButton;
    private JButton lavenderButton;
    
    private static final Color RED = Color.RED;
    private static final Color ORANGE = new Color(255,128,0);
    private static final Color GREEN = new Color(0,128,0);
    private static final Color BLUE = Color.BLUE;
    private static final Color PURPLE = new Color(128,0,140);
    private static final Color BLACK = Color.BLACK;
    private static final Color BROWN = new Color(130,80,50);
    private static final Color GRAY = new Color(150,150,150);
    private static final Color PINK = new Color(255,128,128);
    private static final Color LIME = new Color(0,255,0);
    private static final Color LIGHT_BLUE = new Color(0,255,255);
    private static final Color LAVENDER = new Color(200,128,200);
    
    
    public ColorSelectionPanel(JFrame frame, FunctionTemplatePanel parent){
        this.frame = frame;
        this.parent = parent;
        
        redButton = new JButton();
        orangeButton = new JButton();
        greenButton = new JButton();
        blueButton = new JButton();
        purpleButton = new JButton();
        blackButton = new JButton();
        brownButton = new JButton();
        grayButton = new JButton();
        pinkButton = new JButton();
        limeButton = new JButton();
        lightBlueButton = new JButton();
        lavenderButton = new JButton();
        
        redButton.setBackground(RED);
        orangeButton.setBackground(ORANGE);
        greenButton.setBackground(GREEN);
        blueButton.setBackground(BLUE);
        purpleButton.setBackground(PURPLE);
        blackButton.setBackground(BLACK);
        brownButton.setBackground(BROWN);
        grayButton.setBackground(GRAY);
        pinkButton.setBackground(PINK);
        limeButton.setBackground(LIME);
        lightBlueButton.setBackground(LIGHT_BLUE);
        lavenderButton.setBackground(LAVENDER);
        
        redButton.addActionListener(this);
        orangeButton.addActionListener(this);
        greenButton.addActionListener(this);
        blueButton.addActionListener(this);
        purpleButton.addActionListener(this);
        blackButton.addActionListener(this);
        brownButton.addActionListener(this);
        grayButton.addActionListener(this);
        pinkButton.addActionListener(this);
        limeButton.addActionListener(this);
        lightBlueButton.addActionListener(this);
        lavenderButton.addActionListener(this);
        
        redButton.setActionCommand("red");
        orangeButton.setActionCommand("orange");
        greenButton.setActionCommand("green");
        blueButton.setActionCommand("blue");
        purpleButton.setActionCommand("purple");
        blackButton.setActionCommand("black");
        brownButton.setActionCommand("brown");
        grayButton.setActionCommand("gray");
        pinkButton.setActionCommand("pink");
        limeButton.setActionCommand("lime");
        lightBlueButton.setActionCommand("lightblue");
        lavenderButton.setActionCommand("lavender");
        
        add(redButton);
        add(orangeButton);
        add(greenButton);
        add(blueButton);
        add(purpleButton);
        add(blackButton);
        add(brownButton);
        add(grayButton);
        add(pinkButton);
        add(limeButton);
        add(lightBlueButton);
        add(lavenderButton);
        
        setLayout(new GridLayout(3,4));
        setPreferredSize(new Dimension(150,100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "red": parent.setColor(RED);break;
            case "orange": parent.setColor(ORANGE);break;
            case "green": parent.setColor(GREEN);break;
            case "blue": parent.setColor(BLUE);break;
            case "purple": parent.setColor(PURPLE);break;
            case "black": parent.setColor(BLACK);break;
            case "brown": parent.setColor(BROWN);break;
            case "gray": parent.setColor(GRAY);break;
            case "pink": parent.setColor(PINK);break;
            case "lime": parent.setColor(LIME);break;
            case "lightblue": parent.setColor(LIGHT_BLUE);break;
            case "lavender": parent.setColor(LAVENDER);break;
            
        }
    }
    
}
