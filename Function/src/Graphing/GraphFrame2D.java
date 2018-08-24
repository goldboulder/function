
package Graphing;

import Other.Boundry1D;
import Other.RectangularBoundry;
import Other.UsefulThings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// is this obsolete?
public class GraphFrame2D extends JFrame implements GraphFrame{
    
    private Graph2DPanel panel;
    
    public GraphFrame2D(GridPainter2D g){
        setTitle("Graph Function");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Graph2DPanel(this,g);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
    }
    
    public void addFunction(Graphable2D f){
        panel.addFunction(f);
    }
    
    
    
    private class Graph2DPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
        private JFrame frame;
        private List<Graphable2D> functions;
        private GridPainter2D gridPainter;
        private RectangularBoundry boundry;
        private Dimension panelDimension = new Dimension(800,800);
        private Dimension lastMousePosition = new Dimension(panelDimension.width / 2, panelDimension.height / 2);
        private double zoomScaleChange = 1.2;
        private JLabel mouseCoLabelX = new JLabel("");
        private JLabel mouseCoLabelY = new JLabel("");
        
        
        public Graph2DPanel(JFrame frame, GridPainter2D gridPainter){
            this.frame = frame;
            functions = new ArrayList<>();
            this.gridPainter = gridPainter;
            boundry = new RectangularBoundry(new Boundry1D('x',-6.0,6.0),new Boundry1D('y',-6.0,6.0));
            
            setFocusable(true);
            requestFocusInWindow();
            setBackground(Color.WHITE);
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);
            
            add(mouseCoLabelX);
            add(mouseCoLabelY);
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        }
        
        public void addFunction(Graphable2D function){
            functions.add(function);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            /*
            for (Graphable2D function : functions){
                if (function != null){
                    function.drawResolutionFunction(g,panelDimension,boundry);
                }
            }

            */
            if (gridPainter != null){
                gridPainter.draw(g,panelDimension,boundry);
            }
            
            for (int i = 0; i < functions.size(); i++){
                functions.get(i).draw(g, panelDimension, boundry);
            }
            
       
            // gets rid of function line on top of the screen
            g.setColor(Color.WHITE);
            g.drawLine(0, 0, 5000, 0);
            
            //but background over labels
            g.fillRect(0, 0, 130, 30);
            
            
       
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(panelDimension.width,panelDimension.height);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            lastMousePosition.setSize(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.requestFocus();
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            translate(e.getX() - lastMousePosition.width, e.getY() - lastMousePosition.height);
            lastMousePosition.setSize(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            lastMousePosition.setSize(e.getX(), e.getY());
            drawNumbers(e.getX(),e.getY());
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getWheelRotation() < 0 && boundry.getBoundry('x').spaceCovered() > 1E-6){
                zoom(lastMousePosition,zoomScaleChange);// zoom in
            }
            else{
                zoom(lastMousePosition,1/zoomScaleChange); // zoom out
            }
        }
        
        private void translate(int mouseDx, int mouseDy){
            if (mouseDx != 0){
                
                double dx = (boundry.getBoundry('x').spaceCovered()) * mouseDx / panelDimension.width;
                boundry.getBoundry('x').translateBounds(-dx);
            }
            if (mouseDy != 0){
                double dy = (boundry.getBoundry('y').spaceCovered()) * mouseDy / panelDimension.height;
                boundry.getBoundry('y').translateBounds(dy);
            }
            
            repaint();
        }

        //needs commenting
        private void zoom(Dimension lastMousePosition, double zoomAmount){// zooming in too much with a function causes infinite loop

            //extract info from arguments and fields
            int mouseX = lastMousePosition.width;
            int mouseY = lastMousePosition.height;
            double xCoFirst = UsefulThings.screenCoToGraphCoX(mouseX,panelDimension.width,boundry);
            double yCoFirst = UsefulThings.screenCoToGraphCoY(mouseY,panelDimension.height,boundry);
            double xSize = (boundry.getBoundry('x').spaceCovered());
            double ySize = (boundry.getBoundry('y').spaceCovered());
            
            double xMove = (xSize - xSize/zoomAmount)/2;
            double yMove = (ySize - ySize/zoomAmount)/2;

            double xPercent = mouseX/(double)panelDimension.width;
            double yPercent = mouseY/(double)panelDimension.height;

            
            double newXMin = boundry.getBoundry('x').getLowerBound() + xMove;
            double newXMax = boundry.getBoundry('x').getUpperBound() - xMove;
            double newYMin = boundry.getBoundry('y').getLowerBound() + yMove;
            double newYMax = boundry.getBoundry('y').getUpperBound() - yMove;

            
            boundry.getBoundry('x').setLowerBound(newXMin);
            boundry.getBoundry('x').setUpperBound(newXMax);
            boundry.getBoundry('y').setLowerBound(newYMin);
            boundry.getBoundry('y').setUpperBound(newYMax);
            

            double newXSize = xSize/zoomAmount;
            double newYSize = ySize/zoomAmount;

            double xCoSecond = newXMin + (xPercent*newXSize);
            double yCoSecond = newYMax - (yPercent*newYSize);

            boundry.getBoundry('x').translateBounds(xCoFirst - xCoSecond);
            boundry.getBoundry('y').translateBounds(yCoFirst - yCoSecond);
            
            repaint();
        }

        public void drawNumbers(int mouseX, int mouseY){// makes the panel repaint?
        
            double x = UsefulThings.screenCoToGraphCoX(mouseX,panelDimension.width,boundry);
            double y = UsefulThings.screenCoToGraphCoY(mouseY,panelDimension.height,boundry);
            x = UsefulThings.format(x);
            y = UsefulThings.format(y);

            mouseCoLabelX.setText("x: " + x);
            mouseCoLabelY.setText("y: " + y);

        }
        
    }
    
}

