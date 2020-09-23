
package Class;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Vilrose Daquiado
 */
public class DarkPanel extends JPanel{
        
    private final Color PRIMARY_BG_COLOR = Color.decode("#121212");
    private final Color DISABLED_COLOR = Color.decode("#0D0D0D");   
    private final Color PRIMARY_TEXT_COLOR = Color.decode("#FAFAFA") ;
    private final Color SECONDARY_TEXT_COLOR = Color.decode("#9E9E9E");
    private Dimension panelSize;
    
      
        public DarkPanel(){
            
            panelSize = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.2),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.2));
            
            this.setMinimumSize(panelSize);
            this.setLayout(new GridBagLayout());
            this.setBackground(PRIMARY_BG_COLOR);           
            this.setForeground(PRIMARY_TEXT_COLOR);
            
    
        }
        
          public DarkPanel(boolean disabled){
              
              if(disabled){
                  this.setBackground(DISABLED_COLOR);
                  this.setForeground(SECONDARY_TEXT_COLOR);
              }
          
          }
            
        
        
  
      
}
