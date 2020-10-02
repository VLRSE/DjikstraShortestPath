/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import DijkstraInterface.DjikstraVersion4;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class DarkButton extends JButton implements FocusListener {
    
    private final DjikstraVersion4 outer;

    public DarkButton(String text, boolean enabled, final DjikstraVersion4 outer) {
        super(text);
        this.outer = outer;
        setOpaque(true);
        setBackground(outer.PRIMARY_BTN_BG_COLOR);
        setForeground(outer.PRIMARY_TEXT_COLOR);
        setEnabled(enabled);
        setFont(outer.normalTextFont);
        addFocusListener(this);
        
        if (enabled == false) {
            setForeground(outer.SECONDARY_TEXT_COLOR);
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outer.SECONDARY_BG_COLOR, 2, true), BorderFactory.createEmptyBorder(8, 18, 8, 18)));
            this.setOpaque(false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setBorderPainted(false);
        this.setOpaque(true);
        setBackground(outer.PRIMARY_BTN_BG_COLOR);
        setForeground(outer.PRIMARY_TEXT_COLOR);
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    
}
