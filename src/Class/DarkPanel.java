/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import DijkstraInterface.DjikstraVersion4;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
/**
 *
 * @author Admin
 */
public class DarkPanel extends JPanel {
    
    private final DjikstraVersion4 outer;

    public DarkPanel(final DjikstraVersion4 outer) {
        this.outer = outer;
        this.setBackground(outer.PRIMARY_BG_COLOR);
        this.setForeground(outer.PRIMARY_TEXT_COLOR);
        this.setLayout(new GridBagLayout());
    }
    
}
