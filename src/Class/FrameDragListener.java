/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
 /*A class that extends the MouseAdapter interface to listen and receive mouse events on the frame that enables the frame to be draggable*/
public class FrameDragListener extends MouseAdapter{
         
    private final JFrame frame;
    private  Point mouseDownCoords = null;
         
    // class constructor that requires a JFrame instance as parameter
    public FrameDragListener (JFrame frame){
        this.frame = frame;
    }
        
    @Override
    public void mousePressed(MouseEvent e){
        mouseDownCoords = e.getPoint();
    }
        
    @Override
    public void mouseDragged(MouseEvent e){
        Point currentCoords = e.getLocationOnScreen();
        frame.setLocation(currentCoords.x - mouseDownCoords.x, currentCoords.y- mouseDownCoords.y);
    }
        
    @Override
    public void mouseReleased(MouseEvent e){
        mouseDownCoords = null;
    }
}     
