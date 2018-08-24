/*

 */
package GUI;

import Graphing.Graphable2D;
import Graphing.GridPainter2D;
import Graphing.RectangularGridPainter2D;
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


public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
    
        private IFunctionFrame frame;
        private GridPainter2D gridPainter;
        private RectangularBoundry boundry;
        private Dimension lastMousePosition = new Dimension(getPreferredSize().width / 2, getPreferredSize().height / 2);
        private double zoomScaleChange = 1.2;
        private JLabel mouseCoLabelX = new JLabel("");
        private JLabel mouseCoLabelY = new JLabel("");
        
        
        
        public GraphPanel(IFunctionFrame frame){
            this.frame = frame;
            this.gridPainter = new RectangularGridPainter2D();
            boundry = new RectangularBoundry(new Boundry1D('x',-6.0,6.0),new Boundry1D('y',-6.0,6.0));
            
            setFocusable(true);
            requestFocusInWindow();
            setBackground(Color.WHITE);
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);
            
            add(mouseCoLabelX);
            add(mouseCoLabelY);
            
        }
        /*
        public void addFunction(Graphable2D function){
            functions.add(function);
        }
        */
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            //make a white rectangle accross the entire panel, start fresh
            g.setColor(Color.WHITE);
            g.drawRect(0, 0, getPreferredSize().width, getPreferredSize().height);
            
            if (gridPainter != null){
                gridPainter.draw(g,getPreferredSize(),boundry);
            }
            
            //draw functions
            ArrayList<Graphable2D> functions = frame.getFunctions();
            for (Graphable2D function : functions){
                if (function != null){
                    function.draw(g, getPreferredSize(), boundry, frame.getTime());
                }
            }
            
       
            // gets rid of function line on top of the screen
            g.setColor(Color.WHITE);
            g.drawLine(0, 0, 5000, 0);
            
            //but background over labels
            g.fillRect(0, 0, 130, 30);
            
            drawNumbers(g);
       
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
            //drawNumbers(e.getX(),e.getY());
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
                
                double dx = (boundry.getBoundry('x').spaceCovered()) * mouseDx / getPreferredSize().width;
                boundry.getBoundry('x').translateBounds(-dx);
            }
            if (mouseDy != 0){
                double dy = (boundry.getBoundry('y').spaceCovered()) * mouseDy / getPreferredSize().height;
                boundry.getBoundry('y').translateBounds(dy);
            }
            
            repaint();
        }
        
        /*
        import java.awt.Robot
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        */

        //needs commenting
        private void zoom(Dimension lastMousePosition, double zoomAmount){// zooming in too much with a function causes infinite loop

            //extract info from arguments and fields
            int mouseX = lastMousePosition.width;
            int mouseY = lastMousePosition.height;
            double xCoFirst = UsefulThings.screenCoToGraphCoX(mouseX,getPreferredSize().width,boundry);
            double yCoFirst = UsefulThings.screenCoToGraphCoY(mouseY,getPreferredSize().height,boundry);
            double xSize = (boundry.getBoundry('x').spaceCovered());
            double ySize = (boundry.getBoundry('y').spaceCovered());
            
            double xMove = (xSize - xSize/zoomAmount)/2;
            double yMove = (ySize - ySize/zoomAmount)/2;

            double xPercent = mouseX/(double)getPreferredSize().width;
            double yPercent = mouseY/(double)getPreferredSize().height;

            
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

        public void drawNumbers(Graphics g){//other coodenate systems?
        
            double x = UsefulThings.screenCoToGraphCoX(lastMousePosition.width,getPreferredSize().width,boundry);
            double y = UsefulThings.screenCoToGraphCoY(lastMousePosition.height,getPreferredSize().height,boundry);
            x = UsefulThings.format(x);
            y = UsefulThings.format(y);
            
            g.setColor(Color.BLACK);
            g.drawString("x: " + Double.toString(x), 10,10);
            g.drawString("y: " + Double.toString(y), 10,30);

        }
        
    
    
}
