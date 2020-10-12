/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class DarkTextArea extends JTextArea implements FocusListener {
    
     private final Color PRIMARY_BG_COLOR = Color.decode("#424242");
    private final Color PRIMARY_TEXT_COLOR = Color.decode("#fafafa");
    private final Font NORMAL_FONT = new Font(Font.SANS_SERIF,Font.BOLD, 12);
    private final Font TITLE_FONT = new Font(Font.SERIF,Font.BOLD, 36);

    public DarkTextArea(boolean enabled,  String string, JFrame frame, int row, int col) {
        super(string,row,col);
        setEnabled(enabled);
        setEditable(false);
        addFocusListener(this);
        setForeground(PRIMARY_TEXT_COLOR);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 50, 0, 0),
                     BorderFactory.createLineBorder(PRIMARY_TEXT_COLOR.darker())));
        setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        
        if (enabled == false) {
            this.setOpaque(false);
        }
        this.setWrapStyleWord(true);
        this.setLineWrap(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setEnabled(true);
        this.setOpaque(false);
       
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    
}
