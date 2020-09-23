/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DijkstraInterface;


import Class.DarkFrame;
import java.awt.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Admin
 */
public class DjikstraVersion4 extends JFrame implements ActionListener {
    public static DarkFrame frame;
    public static JPanel mainPanel1, mainPanel2;
    public static JScrollPane leftScrollPane, rightScrollPane ;
    public static JSplitPane splitPane;
    public static JTextArea edgesCollectionTxtArea, solutionTxtArea;
    public static JLabel graphLabel ,solutionLabel, vertex1Label, vertex2Label, distanceLabel, inputEdgesLabel;
    public static JButton defineGraphBTN,  addEdgesBTN, solutionBTN, newGraphBTN;
    public static JTextField inputEdgesTxtFLD, vertex1TextField, vertex2TextField, distTextField;
    public static Container contentPane;
    
    public static final Color PRIMARY_BG_COLOR = Color.decode("#212121");
    public static final Color SECONDARY_BG_COLOR = Color.decode("#424242");
    
    public static final Color PRIMARY_TEXT_COLOR = Color.decode("#E0E0E0");
    public static final Color SECONDARY_TEXT_COLOR = Color.decode("#F5F5F5");
    
    public static final Color PRIMARY_BG_TEXTFLD_COLOR = Color.decode("#616161");
    
    public static final Color PRIMARY_BTN_BG_COLOR = Color.decode("#2979FF");
    private static final long serialVersionUID = 1L;


    public static void main(String[] args) throws FileNotFoundException{
       frame = new DarkFrame("Djikstra´s Shortest Path Algorithm");
       
//       double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.7;
//       double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7;
//       
//       frame.setMinimumSize(new Dimension((int)width, (int)height));
       
       createGUI();
       
       
       frame.setVisible(true);
    }
    
 
    //initialize all the components
    public static void createGUI(){       
        
        
        contentPane = frame.getContentPane();
        
        createMainPanel1();      
        
        /*
        *TODO: add components to the right side of the panel
        */
        
        /*
        *create mainPanel2, on the right, and its components
        */
        solutionLabel = createLabel( "SOLUTION", JLabel.CENTER);
        solutionTxtArea = createTextArea(true );    
         
        
        solutionBTN = createButton(" SOLUTION ", false );
        newGraphBTN = new JButton(" NEW GRAPH ");
        
        /*create a JPanel object to hold the components of the main panel*/
        
        JPanel panelInputEdges;
        GridBagConstraints constraints;
        
        
        constraints = createConstraints(true, 0, 0, 3, 1);
        panelInputEdges = createPanel( new FlowLayout(), solutionBTN, FlowLayout.LEADING);
        panelInputEdges.add(newGraphBTN, FlowLayout.CENTER);
        
        
        Component[] mainPanel2Components = { solutionTxtArea, panelInputEdges };
        
        mainPanel2 = createMainPanels(mainPanel1, solutionLabel, mainPanel2Components); 
        
       

         //create JScrollPane objects          
        Dimension minimumSize = new Dimension(100, 50);
        
        leftScrollPane = new JScrollPane(mainPanel1);        
        rightScrollPane = new JScrollPane(mainPanel2);     
        leftScrollPane.setMinimumSize(minimumSize);
        rightScrollPane.setMinimumSize(minimumSize);
       
         //create a JSplitPane object
        splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                           leftScrollPane, rightScrollPane); 
         //set equal size of the two components on JSplitpane 
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);    
        
        
        /*
        *get contentPane, and add the splitPane to the contentPane
        */
        contentPane = frame.getContentPane();
        contentPane.add(splitPane);

   
    }
    // create and add components to the panel on the left of the frame
    public static void createMainPanel1(){
        
        /*
        *TODO: CHANGE LAYOUTMANAGER OF inputPANELS
        */
        JPanel panelInputEdges, vertex1Panel, vertex2Panel, distancePanel, edgesCollectionPanel;
        GridBagConstraints constraints;
        
        constraints = createConstraints(true, 0, 0, 1, 1);
        
        //create top Label
        graphLabel = createLabel( "GRAPH", JLabel.CENTER);
        
       

       /*
       *create Components for the inputTextField, then add to a panel 
       */
       
       inputEdgesLabel = createLabel( "Number of edges: ", JLabel.CENTER);
       inputEdgesTxtFLD =  createTextField("e.g. 2 ", 5);
       defineGraphBTN = new JButton("DEFINE GRAPH");
        
        panelInputEdges = createPanel(new GridBagLayout(), inputEdgesLabel, constraints);
        constraints.gridx = 1;
        panelInputEdges.add(inputEdgesTxtFLD, constraints);
        
        constraints.gridx = 3;
        constraints.anchor = GridBagConstraints.LINE_END;
        panelInputEdges.add(defineGraphBTN, constraints);        
        
        /*
       *create Components for the FROM inputField, then add to a panel 
       */
        constraints.gridx = 0;
        vertex1Label = new JLabel("From : ");
        vertex1Label.setForeground(SECONDARY_TEXT_COLOR);
        vertex1TextField = createTextField("e.g. A or Milan ", 30);
        
        vertex1Panel  = createPanel( new GridBagLayout(), vertex1Label, constraints);      
        vertex1Panel.add(vertex1TextField);
        
         /*
       *create Components for the TO inputField, then add to a panel 
       */
         
        vertex2Label = new JLabel("To : ");
        vertex2Label.setForeground(SECONDARY_TEXT_COLOR);
        vertex2TextField = createTextField("e.g. B or Rome ", 30);
        
        vertex2Panel  = createPanel( new GridBagLayout(), vertex2Label, constraints);      
        vertex2Panel.add(vertex2TextField);
         /*
       *create Components for the DISTANCE inputField, then add to a panel 
       */
        distanceLabel = new JLabel("DISTANCE: ");        
        distTextField = createTextField("e.g. 5 ", 30);
        
         /*
       *create Components for the edgesCollectionTxtArea, then add to a panel 
       */
        addEdgesBTN = new JButton("ADD EDGES");
        edgesCollectionTxtArea = createTextArea(false );    
         
        
        distancePanel = createPanel( new GridBagLayout(), addEdgesBTN, constraints); 
        
        /*
        *Add components to the mainPanel1
        */
        Component[] mainPanel1Components = { panelInputEdges, vertex1Panel, vertex2Panel, distancePanel, edgesCollectionTxtArea};
        mainPanel1 = createMainPanels(mainPanel1, graphLabel, mainPanel1Components);

    }
    
 
    
    //create the main panels and add its components onto it
    public static JPanel createMainPanels(JPanel mainPanel, JLabel panelsLabel, Component[] components){
        //components constraints
        GridBagConstraints constraints = createConstraints(true, 0, 0, 3, 1);
        
        //create main panels with labels
        constraints.insets = new Insets(5,0,50,5);          
        panelsLabel.setFont(createFont(false));
        
        //create mainPanel
        mainPanel = createPanel( new GridBagLayout(), panelsLabel, constraints);
        
        //reset insets for the rest of the components
        constraints.insets = new Insets(0,0,10,0);    
        constraints.gridwidth = 1;
        
        
//        Add components to the main panels   
        for( Component component : components){
            constraints.gridy += 1;
            mainPanel.add(component, constraints);
        }
        
        return mainPanel;
    }
    
    //create an JPanel object
    public static JPanel createPanel( LayoutManager layout, Component component, Object constraints){
        JPanel panel = new JPanel(layout);
        panel.setBackground(PRIMARY_BG_COLOR);
        panel.add(component, constraints);
        
        return panel;
    }
    
    //create a dark-themed JLabel object
    public static JLabel createLabel(String text, int  textAlignment ){
        JLabel label = new JLabel(text);
        label.setBackground(PRIMARY_BG_COLOR);
        label.setForeground(PRIMARY_TEXT_COLOR);
        label.setHorizontalAlignment(textAlignment);
        
        return label;
    }
    
    public static JTextField createTextField(String promptText, int columns){
        JTextField textField = new JTextField(promptText, columns);
        //        textField.setPreferredSize(new Dimension(100,25));

        textField.setBackground(PRIMARY_BG_TEXTFLD_COLOR);      
        textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        textField.setDisabledTextColor(PRIMARY_BG_COLOR);
        textField.setForeground(SECONDARY_TEXT_COLOR);
        textField.setFont(createFont(true));
        textField.setHorizontalAlignment(SwingConstants.LEADING);
        
        return textField;
    }
    
       public static JTextArea createTextArea(boolean disabled){
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(PRIMARY_BG_TEXTFLD_COLOR);
        textArea.setDisabledTextColor(PRIMARY_BG_COLOR);
        textArea.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.2),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.2)));
        if(disabled){
           textArea.setEnabled(false);
           textArea.setBackground(PRIMARY_BG_COLOR);
           textArea.setBorder(BorderFactory.createLineBorder(SECONDARY_BG_COLOR, 1, true));
        }

        return textArea;
        
    }
    
    public static JButton createButton(String text, boolean disabled ){
        JButton button = new JButton(text);
        
        if(disabled){
            button.setEnabled(false);
            button.setBackground(PRIMARY_BG_COLOR);
        }
        else{
         button.setBackground(PRIMARY_BG_COLOR);
        }
        button.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
        button.setForeground(PRIMARY_TEXT_COLOR);
        
        
        return button;
    }

    public static GridBagConstraints createConstraints(boolean shouldFill, int gridX, int gridY, int gridWeight, int gridHeight){
        GridBagConstraints c = new GridBagConstraints();
        
        if(shouldFill){
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        
        c.ipadx = 0;
        c.gridx = gridX;
        c.gridy = gridY;
        c.gridwidth = gridWeight;
        c.gridheight = gridHeight;
        
        return c;
    }   
    
    
    public static Font createFont( boolean primaryText){
        Font titleFont;
        
        if(!primaryText){
            titleFont = new Font("Sans Serif", Font.BOLD, 24);
            return titleFont;
        }
        else{
            titleFont = new Font("Sans Serif", Font.PLAIN, 12);
             return titleFont;
        }        
       
    }
    
    public static Color getTransparentColor(Color color){
        int red = color.getRed();
        int blue = color.getBlue();
        int green = color.getGreen();
        int alpha = (int)(color.getAlpha()*0.5);
        
       Color transparentColor = new Color(red, green, blue, alpha);
       
        return transparentColor;
    }
    
    public void actionPerformed(ActionEvent e) { 
   
    }
    
    
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
}
