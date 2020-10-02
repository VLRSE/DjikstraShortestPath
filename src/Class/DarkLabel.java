/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class DarkLabel extends JLabel {
    
    private final Color PRIMARY_BG_COLOR = Color.decode("#424242");
    private final Color PRIMARY_TEXT_COLOR = Color.decode("#fafafa");
    private final Font NORMAL_FONT = new Font(Font.SANS_SERIF,Font.BOLD, 12);
    private final Font TITLE_FONT = new Font(Font.SERIF,Font.BOLD, 24);

        
    public DarkLabel(String text, boolean isTitle) {
        super(text);
        setEnabled(false);
        setBackground(PRIMARY_BG_COLOR);
        setForeground(PRIMARY_TEXT_COLOR);
        this.setHorizontalAlignment(JLabel.CENTER);
        
        if (isTitle == true) {
            setFont(TITLE_FONT);
            setHorizontalAlignment(JLabel.CENTER);
        }
        else {
            setFont(NORMAL_FONT);
            setHorizontalAlignment(JLabel.LEADING);
        }
    }    
        public DarkLabel(String text) {
        super(text);
        setEnabled(false);
        setBackground(PRIMARY_BG_COLOR);
        setForeground(PRIMARY_TEXT_COLOR);
//        this.setAlignmentX(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
       
            setFont(NORMAL_FONT);
            setHorizontalAlignment(JLabel.CENTER);
        
        
         
        
    }
    
}
