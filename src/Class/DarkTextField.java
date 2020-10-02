/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import DijkstraInterface.DjikstraVersion4;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;


/**
 *
 * @author Admin
 */
public class DarkTextField extends JTextField implements FocusListener {
    private boolean isEnabled;    
    private final DjikstraVersion4 outer;

    public DarkTextField(String text, int columns, boolean enabled, final DjikstraVersion4 outer) {
        super(text, columns);
        
        this.isEnabled = enabled;
        this.outer = outer;
        addFocusListener(this);
        setProperties();
       
    }
    public void setProperties(){
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outer.SECONDARY_BG_COLOR, 2),
                 BorderFactory.createEmptyBorder(8, 18, 8, 10)));
        setDisabledTextColor(outer.SECONDARY_BG_COLOR.brighter());
        setFont(outer.promptTextFont);
        setEnabled(isEnabled);
        if (this.isEnabled() == false) {
            setFont(outer.promptTextFont);
            setOpaque(false);
            setForeground(outer.SECONDARY_BG_COLOR);
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        setText("");
        setEnabled(true);
        setOpaque(true);
        setBackground(outer.PRIMARY_BG_TEXTFLD_COLOR);
        setForeground(outer.SECONDARY_BG_COLOR);
        setFont(outer.normalTextFont);
          setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#3498db"), 2, true),
                    BorderFactory.createEmptyBorder(8, 18, 8, 10)));
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outer.SECONDARY_BG_COLOR.brighter(), 2),
                BorderFactory.createEmptyBorder(8, 18, 8, 10)));
        setOpaque(false);
        setForeground(outer.PRIMARY_BG_TEXTFLD_COLOR.darker());
    }
    
    
    
}
