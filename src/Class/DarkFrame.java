package Class;

import Classes.FrameDragListener;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
/*
 * a dark-themed Frame 
 *  Initial version May 2020
 */

/**
 *
 * @author Vilrose Daquiado
 */

public class  DarkFrame extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    private JButton BTNExit, BTNMinimize, BTNMaximize, BTNReduceSize;   
    private  JPanel topRightPanel, topPanel;
    private final Color DARKER_GRAY = Color.decode("#212121"), LIGHTER_GRAY = Color.decode("#424242");
    private final Container pane = this.getContentPane();    
    private Dimension preferredSize;
    private final String appName;
    
    public DarkFrame( String appName) {
        super(appName);
        this.appName = appName;
      
        createFrame();
        pack();
        
        //set Location at the center of the screen
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    public Container getPane() {
        return pane;
    }
   
    // create a JFrame instance and set its property
    public void createFrame() { 
        // JFrame´s property set-up      
       
        Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
        preferredSize = new Dimension((int)(screenSize.getWidth()*0.5),  (int)(screenSize.getHeight()*0.5));
        setPreferredSize(preferredSize);

        //set JFrame´s bounds to center of the screen
        
        pane.setBackground(DARKER_GRAY);
        setLayout(new BorderLayout(10,3));
        setUndecorated(true);
        //Frame´s size is maximied upon running   
        setResizable(true);
        
        /*add a mouse and mouseMotion listeners to the frame receive the mouse events on this frame*/
       FrameDragListener frameragListener = new FrameDragListener(this);
       this.addMouseListener(frameragListener);
       this.addMouseMotionListener(frameragListener);
       
       
        //create a topPanel of the frame
        topPanel = addPanel(pane,pane.getWidth(),20, new BorderLayout(),LIGHTER_GRAY,BorderLayout.NORTH);
       
        topPanel.add(createTitlePanel(), BorderLayout.LINE_START);
        topPanel.add( addBTNExitMinMaxPanel());
         topPanel.setVisible(true);
        
       
       //Add top-right buttons for close, minimize and maximize
       

    }
    
   
     //A method that requires a String, Color, and ImageIcon objects as parameters; instantiates a JButton object, set its properties, and returns a JButton Object
    public JButton createButton( String text, Color bgColor,  ImageIcon icon, String toolTipText){
        JButton newButton = new JButton(text);

        newButton.setBackground(bgColor);
        newButton.setHorizontalAlignment(SwingConstants.CENTER);
        newButton.setForeground(new Color(250,250,250));
        newButton.setIcon(icon);
        newButton.setToolTipText(toolTipText);
        newButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        newButton.setFocusPainted(false);
        newButton.addActionListener(this);
      
        return newButton;
    }
    
     //A method to add all the buttons on the top-right corner of the menu bar such as Exit, maximize, minimize and reduce-screen-size buttons
    public JPanel addBTNExitMinMaxPanel(){ 
        ImageIcon icon;
        
        topRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        topRightPanel.setOpaque(false);
        
        //Create Top-right Buttons
        icon = new ImageIcon(getClass().getResource("/resources/minimize_white_18dp.png"));
        BTNMinimize = createButton( "", LIGHTER_GRAY, icon,"Minimieren");  
        
        icon = new ImageIcon(getClass().getResource("/resources/max.png"));
        BTNMaximize = createButton( "", LIGHTER_GRAY, icon,"Maximieren");
        
        icon = new ImageIcon(getClass().getResource("/resources/reduce.png"));
        BTNReduceSize = createButton( "", LIGHTER_GRAY, icon,"Maximieren");
        BTNReduceSize.setVisible(false);
        
        icon = new ImageIcon(getClass().getResource("/resources/close.png"));
        BTNExit = createButton( "", LIGHTER_GRAY, icon,"Maximieren");
        
        
         //adding the buttons on the panel        
        topRightPanel.add(BTNMinimize); 
        BTNMinimize.setVisible(true);
        topRightPanel.add(BTNMaximize);  
        topRightPanel.add(BTNReduceSize);
        topRightPanel.add( BTNExit);
        topRightPanel.setVisible(true);
    
        return topRightPanel;    
    }
    public JPanel createTitlePanel(){
        JPanel titlePanel = new JPanel();
        JLabel titleLabel ;
        
        titlePanel.setOpaque(false);
        
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        
        titleLabel =  new JLabel(appName, SwingConstants.CENTER);
        titleLabel.setForeground(Color.decode("#ffffff"));
        
        titlePanel.add(titleLabel);
        return titlePanel;    
    }
    
    //a method that adds a JPanel instance that contains centerPanel components    
    
    public JPanel addPanel(Container host,  int width, int height, LayoutManager layout, Color bgColor, String position){
        
        JPanel panel = new JPanel(layout);
        panel.setBackground(bgColor);
        panel.setSize(width, height);   
        panel.revalidate();
        panel.repaint();        
        panel.setVisible(true);  
        host.add(panel, position);
        return panel;
    }    
  

    @Override
    public void actionPerformed(ActionEvent e) {
         Object source = e.getSource();
         
         
        //if the user has clicked the exit (X) button, the JFrame state will be disposed
        if(source.equals(BTNExit)){
            super.dispose();
        }
         // if the source is the minimize  (-) button has been clicked, the JFrame´s state will be iconified or hidden but keeps running on the background
        else if(source.equals(BTNMinimize)){
            super.setState(Frame.ICONIFIED);
        }
          // if the source is the reducescreensiuze or double-square icon, the JFrame screen size will be reduced to Dimension(800, 500);
        else if(source.equals(BTNReduceSize)){
            super.setExtendedState(JFrame.NORMAL);
            //after it had been clicked, the button´s visible state is changed to false making it invisible on the Frame
            BTNReduceSize.setVisible(false);
            //the MAX Button or one-square icon will replace the double square button´s position
            BTNMaximize.setVisible(true);       
        }
          
        else if(source.equals(BTNMaximize)){
            super.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //after it had been clicked, the button´s visible state is changed to false making it invisible on the Frame
            BTNMaximize.setVisible(false);
            //the reduce screen size Button or two-square icon will replace the double square button´s position
            BTNReduceSize.setVisible(true);
        }
    }
  
     
    
    
}