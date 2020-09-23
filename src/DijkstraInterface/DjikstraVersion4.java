
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
    
    DarkFrame frame;
    JPanel mainPanel1, mainPanel2;
    JScrollPane leftScrollPane, rightScrollPane ;
    JSplitPane splitPane;
    JTextArea edgesCollectionTxtArea, solutionTxtArea;
    JLabel graphLabel ,solutionLabel, vertex1Label, vertex2Label, distanceLabel, inputEdgesLabel;
    JButton defineGraphBTN,  addEdgesBTN, solutionBTN, newGraphBTN;
    JTextField inputEdgesTxtFLD, vertex1TextField, vertex2TextField, distTextField;
    Container contentPane;
    
    Dimension minimumSize;
     
     
    Color PRIMARY_BG_COLOR = Color.decode("#121212");
    Color SECONDARY_BG_COLOR = Color.decode("#424242");
    
    Color PRIMARY_TEXT_COLOR = Color.decode("#E0E0E0");
    Color SECONDARY_TEXT_COLOR = Color.decode("#F5F5F5");
    
    Color PRIMARY_BG_TEXTFLD_COLOR = Color.decode("#616161");
    
    Color PRIMARY_BTN_BG_COLOR = Color.decode("#2979FF");


    
    
        public DjikstraVersion4(){
         
            frame = new DarkFrame("Djikstra´s Shortest Path Algorithm");
             
            /*
            *create Main Panels
            */
            mainPanel1 = new DarkPanel(); 
            mainPanel2 = new DarkPanel(); 
            /*
            *create JscrollPane objects for left and right panels of splitPane
            */
            
            minimumSize = new Dimension(100, 50);        
            leftScrollPane = new JScrollPane(mainPanel1);        
            rightScrollPane = new JScrollPane(mainPanel2);     
            leftScrollPane.setMinimumSize(minimumSize);
            rightScrollPane.setMinimumSize(minimumSize);
            
            //create a JSplitPane object
            splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                           leftScrollPane, rightScrollPane); 
            splitPane.setResizeWeight(0.5);
            splitPane.setOneTouchExpandable(true);
            
            
             /*
            *get contentPane, and add the splitPane to the contentPane
            */
          
             contentPane = frame.getContentPane();
      

            /*
            *Create Components of the leftScrollPane
            */            
            graphLabel = new DarkLabel( "GRAPH");
            
            inputEdgesLabel = new DarkLabel( "Number of edges: ");
            inputEdgesTxtFLD =  new JTextField("e.g. 2 ", 5);
            defineGraphBTN = new JButton("DEFINE GRAPH");
            defineGraphBTN.addActionListener(this);
            
              /*
            *create Components for the FROM inputField, then add to a panel 
            */
            vertex1Label = new DarkLabel("From : ");            
            vertex1TextField =  new JTextField("e.g. A or Milan ", 30);
        
            /*
            *create Components for the TO inputField, then add to a panel 
            */
            vertex2Label = new DarkLabel("To : ");
            vertex2TextField =  new JTextField("e.g. B or Rome ", 30);

            /*
            *create Components for the DISTANCE inputField, then add to a panel 
            */
            distanceLabel = new DarkLabel("Distance: ");        
            distTextField =  new JTextField("e.g. 5 ", 30);
        
          /*
          *create Components for the edgesCollectionTxtArea, then add to a panel 
          */
            addEdgesBTN = new JButton("ADD EDGES");
            addEdgesBTN.addActionListener(this);
            edgesCollectionTxtArea =  new DarkTextArea( true);    
            
            /*
          *create Components for the right-side of the splitPane
          */
            solutionLabel = new DarkLabel("SOLUTION");
            solutionTxtArea = new DarkTextArea(true);  
            solutionBTN = new JButton(" SOLUTION " );
            newGraphBTN = new JButton(" NEW GRAPH ");
            
            
            createGUI();
            frame.pack();
        }
        
        
       //initialize all the components
    public void createGUI(){       
        
        contentPane = frame.getContentPane();
        
        //create MainPanel1 -- lies on the left side of the frame
        createMainPanel1();      
        
        //create MainPanel2 -- lies on the right side of the frame
        createMainPanel2();
        
        /*
        *get contentPane, and add the splitPane to the contentPane
        */
        contentPane = frame.getContentPane();
        contentPane.add(splitPane);
   
    }
    
    
    // create and add components to the panel on the left of the frame
    public void createMainPanel1(){
        
        /*
        *TODO: CHANGE LAYOUTMANAGER OF inputPANELS
        */
        JPanel panelInputEdges, vertex1Panel, vertex2Panel, distancePanel, addEdgesBTNPanel;
        GridBagConstraints constraints;
        
        constraints = createConstraints(true, 0, 0, 1, 1);
        constraints.weightx = 0.3;
        
        panelInputEdges = new DarkPanel();
        vertex1Panel  = new DarkPanel();
        vertex2Panel  = new DarkPanel();
        distancePanel = new DarkPanel();
        addEdgesBTNPanel = new DarkPanel();
        /*
        *Add the labels on every panels
        */        
        panelInputEdges.add(inputEdgesLabel, constraints);
        vertex1Panel.add(vertex1Label, constraints);
        vertex2Panel.add(vertex2Label, constraints);
        distancePanel.add(distanceLabel, constraints);
        
         /*
        *Add the textFields components on every panels
        */
        constraints.gridx = 2;
        panelInputEdges.add(inputEdgesTxtFLD, constraints);
        vertex1Panel.add(vertex1TextField);
        vertex2Panel.add(vertex2TextField);
        distancePanel.add(distTextField, constraints);
         /*
        *Add the button on every panels
        */        
//     
        constraints.gridx = 3;
        constraints.insets = new Insets(0,20,0,0);
        panelInputEdges.add(defineGraphBTN, constraints);   
        
         constraints.gridx = 2;
         constraints.fill = GridBagConstraints.NONE;
        addEdgesBTNPanel.add(addEdgesBTN, constraints);
        
        
        /*
        *Add components to the mainPanel1
        */
        Component[] mainPanel1Components = {graphLabel, panelInputEdges, vertex1Panel, vertex2Panel, 
                    distancePanel,addEdgesBTNPanel, edgesCollectionTxtArea};
        
        //call the add components method to add components to the mainPanel1
        addComponents(mainPanel1, graphLabel, mainPanel1Components);

    }
     
    /*
    *create mainPanel2, on the right of the frame, and its components
    */
    public void createMainPanel2(){
  
        /*create a JPanel object to hold the components of the main panel*/
        
        JPanel panelInputEdges;
        GridBagConstraints constraints;
        
        constraints = createConstraints(true, 0, 0, 1, 1
        );
        
        panelInputEdges = new DarkPanel();
        panelInputEdges.add(solutionBTN, constraints);
        
//        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.insets = new Insets(0,30,0,10);
        panelInputEdges.add(newGraphBTN, constraints);
        
        Component[] components = {solutionLabel,  solutionTxtArea, panelInputEdges };

        //call the add components method to add components to the mainPanel2        
        addComponents(mainPanel2, solutionLabel, components); 
    
    }
    
    //create the main panels and add its components onto it
    public void addComponents(JPanel mainPanel, JLabel panelsLabel, Component[] components){
        //components constraints
        GridBagConstraints constraints = createConstraints(true, 0, 0, 3, 1);
        
        //create main panels with labels
        constraints.insets = new Insets(5,0,50,5);          
        panelsLabel.setFont(createFont(false));
        
        //reset insets for the rest of the components
        constraints.insets = new Insets(0,0,10,0);    
        constraints.gridwidth = 1;
        
        
//        Add components to the main panels   
        for( Component component : components){
            constraints.gridy += 1;
            mainPanel.add(component, constraints);
        }
        
    }
    
    public JTextField createTextField(String promptText, int columns){
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
    
       
    
    public JButton createButton(String text, boolean disabled ){
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

    public GridBagConstraints createConstraints(boolean shouldFill, int gridX, int gridY, int gridWeight, int gridHeight){
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
    
    
    public Font createFont( boolean primaryText){
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
    
    public Color getTransparentColor(Color color){
        int red = color.getRed();
        int blue = color.getBlue();
        int green = color.getGreen();
        int alpha = (int)(color.getAlpha()*0.5);
        
       Color transparentColor = new Color(red, green, blue, alpha);
       
        return transparentColor;
    }
    /*
    **TODO: create actionPerformed for defineGraphBTN and addEdgesBTN
    */
    public void defineGraphBTNActionPerformed(ActionEvent e) { 
   
    /*
    **TODO: create actionPerformed for defineGraphBTN and addEdgesBTN
    */
        
    }
    
     public void addEdgesBTNActionPerformed(ActionEvent e) { 
   
    /*
    **TODO: create actionPerformed for defineGraphBTN and addEdgesBTN
    */
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        /*
        *TODO:  
        */
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
    
    public class DarkTextArea extends JTextArea{
        
        
        public DarkTextArea(boolean disabled){
        
            this.setEditable(false);
            this.setBackground(PRIMARY_BG_TEXTFLD_COLOR);
            this.setDisabledTextColor(PRIMARY_BG_COLOR);
            this.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.2),
                    (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.2)));
            
            if(disabled){
                this.setEnabled(false);
                this.setBackground(PRIMARY_BG_COLOR);
                this.setBorder(BorderFactory.createLineBorder(SECONDARY_BG_COLOR, 1, true));
            }
        }
        
    }
    
    public class DarkLabel extends JLabel{

        private final long serialVersionUID = 1L;
        
        private String text;
        
        public DarkLabel(String text){
            
            super(text);
            this.setText(text);
            this.setBackground(PRIMARY_BG_COLOR);
            this.setForeground(PRIMARY_TEXT_COLOR);
            this.setHorizontalAlignment(JLabel.CENTER);
        }
        
    }
    
    
    
    
        
    public static void main(String[] args) throws FileNotFoundException{
            
           EventQueue.invokeLater(new Runnable(){
               @Override
               public void run() {
                  new DjikstraVersion4();
               }
               
           });
               
       
      }
    
}
