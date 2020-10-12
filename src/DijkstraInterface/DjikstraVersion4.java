
package DijkstraInterface;


import Class.DarkButton;
import Class.DarkFrame;
import Class.DarkLabel;
import Class.DarkPanel;
import Class.DarkTextArea;
import Class.DarkTextField;
import Class.Graph;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
/**
 *
 * @author Admin
 */
public class DjikstraVersion4 extends JFrame implements ActionListener, KeyListener, FocusListener{
    
    
    public DarkFrame frame;
    public JPanel mainPanel1, mainPanel2;
    public JScrollPane leftScrollPane, rightScrollPane ;
    public JSplitPane splitPane;
    public JTextArea edgesCollectionTxtArea, solutionTxtArea;
    public JLabel graphLabel ,solutionLabel, vertex1Label, vertex2Label, distanceLabel, inputEdgesLabel;
    public JButton defineGraphBTN,  addEdgesBTN, solutionBTN, newGraphBTN;
    public JTextField inputEdgesTxtFLD, vertex1TextField, vertex2TextField, distTextField;
    public Container contentPane;
    
    public ArrayList <JComponent>sources, nextObject;         
          
    public final Color PRIMARY_BG_COLOR = Color.decode("#262524");
    public final Color SECONDARY_BG_COLOR = Color.decode("#131b26");    
    public final Color PRIMARY_TEXT_COLOR = Color.decode("#fafafa");
    public final Color SECONDARY_TEXT_COLOR = Color.decode("#F5F5F5");    
    public final Color PRIMARY_BG_TEXTFLD_COLOR = Color.decode("#f5f5f5");
//    public final Color SECONDARY_BG_TEXTFIELD_COLOR = Color.decode("#757575");    
    public final Color PRIMARY_BTN_BG_COLOR = Color.decode("#2979FF");    
    public final Color ERROR_COLOR = Color.decode("#CF6679");
    
    public Font titleFont, normalTextFont, promptTextFont;
    public String[] textAreaTitles = {" START \t\tEND\tDISTANCE\n\n", "\tPATH\n"};
    public int defineGraphBTNClickCounter = 0;
    private final long serialVersionUID = 1L;
    
    
    private  Graph.Edge[] EDGES;
    private  Graph GRAPH ;
    private  String START = "";
    private  String END = "";    
    private  String str = "";
    private  int edgeCount = 0;
    private int x, distanceInput;
    private String vertex1Input, vertex2Input;
    private ArrayList<Object> vertex1Array, vertex2Array;
       
    
        public DjikstraVersion4(){
                 super.setResizable(true);
                initComponents();
               
    
        }
            
    public void initComponents(){
        
        frame = new DarkFrame("Djikstra´s Shortest Path Algorithm");
        sources = new ArrayList<>();
               
        mainPanel1 = new DarkPanel(this);
        mainPanel2 = new DarkPanel(this);
                 
                 
                  //create JScrollPane objects          
        Dimension minimumSize = new Dimension(150, 50);
        
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
        
        //create customize fonts
        titleFont = new Font("Veranda", Font.BOLD, 24);
        normalTextFont = titleFont.deriveFont(Font.BOLD, 12);
        promptTextFont = normalTextFont.deriveFont(Font.ITALIC, 12);
                                
        //create Components for the EDGES inputField, then add to a panel   
        graphLabel = new DarkLabel("GRAPH", true);
        
        inputEdgesLabel = new DarkLabel( "Number of edges: ");
        inputEdgesTxtFLD =  new DarkTextField(" e.g. 2 ", 5, true, this);
        sources.add(inputEdgesTxtFLD);
                
        defineGraphBTN =  new DarkButton("Define Graph", true, this);
        sources.add(defineGraphBTN);

        //create Components for the FROM inputField, then add to a panel   
        vertex1Label = new DarkLabel("Vertex1(Start) : ");
        vertex1TextField = new DarkTextField("e.g. A or Milan ", 20, false, this);
        sources.add(vertex1TextField);

         //create Components for the TO inputField, then add to a panel        
        vertex2Label = new DarkLabel("Vertex2 (End): ");
        vertex2TextField = new DarkTextField("e.g. B or Rome ", 20 , false, this);
        sources.add(vertex2TextField);


         //create Components for the DISTANCE inputField, then add to a panel                 
        distanceLabel = new DarkLabel("Distance: ");        
        distTextField = new DarkTextField(" e.g. 5 ", 20 , false, this);
        sources.add(distTextField);
        
         /*
        *create Components for the edgesCollectionTxtArea, then add to a panel 
        */
        addEdgesBTN = new DarkButton("ADD EDGES", false, this);
        addEdgesBTN.addActionListener(this);
        sources.add(addEdgesBTN);
        
        DarkLabel label = new DarkLabel("START");
        edgesCollectionTxtArea = new DarkTextArea(false,  textAreaTitles[0], frame, 10,20 ); 
        sources.add(edgesCollectionTxtArea);

        /*
        *create components for mainPanel2, which lie on the right side of the frame
        */

        solutionBTN = new DarkButton("Solution", false, this);
        sources.add(solutionBTN);

        solutionLabel = new DarkLabel( "SOLUTION", true);
        newGraphBTN = new DarkButton("New Graph" , false, this);
        sources.add(newGraphBTN);
        solutionTxtArea = new DarkTextArea(false,  textAreaTitles[1], frame , 10,20);      
        sources.add(solutionTxtArea);

        

        contentPane = frame.getContentPane();

        createGUI();   
       
        frame.pack();
        frame.setLocationRelativeTo(null);
            
    }
    //initialize all the components
    public void createGUI(){  
        //ad keyListeners to the textfields and buttons
        defineGraphBTN.addActionListener(this);
        solutionBTN.addActionListener(this);
        newGraphBTN.addActionListener(this);
        
        //add KeyListeners to  JComponents
        JComponent[] components = {defineGraphBTN,inputEdgesTxtFLD,vertex1TextField, vertex2TextField,distTextField,solutionBTN,newGraphBTN, addEdgesBTN };
        addKeyListener(components, true, false);

        vertex1Array = new ArrayList<>();
        vertex2Array = new ArrayList<>();
        
        //create MainPanel  for the leftScrollPane
        createMainPanel1();  
        
        //create MainPanel  for the rightScrollPane
        createMainPanel2();  
        
        //add the splitPane, and all of the components on it, to the contentPane        
        contentPane.add(splitPane);
    }
    
    // create and add components to the panel on the left of the frame
    public void createMainPanel1(){
        System.out.println("frame size " + this.getContentPane().getSize());
        JPanel inputEdgesPanel, vertex1Panel, vertex2Panel, distancePanel, addEdgesBTNPanel, edgesCollectionPanel;
        GridBagConstraints constraints;
        
        constraints = createConstraints(true, 0, 0, 1, 1);

        inputEdgesTxtFLD.setFont(promptTextFont);
              
        inputEdgesLabel.setHorizontalAlignment(JLabel.CENTER);
        vertex1Label.setHorizontalAlignment(JLabel.CENTER);
        vertex2Label.setHorizontalAlignment(JLabel.CENTER);
        distanceLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //create Panels for each horizontal components              
        inputEdgesPanel = new DarkPanel(this);
        vertex1Panel  = new DarkPanel(this);
        vertex2Panel = new DarkPanel(this);
        distancePanel = new DarkPanel(this);
        addEdgesBTNPanel = new DarkPanel(this);
        edgesCollectionPanel = new DarkPanel(this);
         
         constraints.weightx = 0.5;
         
        //add each labels to the respective panels
        inputEdgesPanel.add(inputEdgesLabel, constraints);
        vertex1Panel.add(vertex1Label, constraints);
        vertex2Panel.add(vertex2Label, constraints);      
        distancePanel.add(distanceLabel, constraints);
        
        //add each textField to the respective panels
        constraints.gridx = 1;
      
        inputEdgesPanel.add(inputEdgesTxtFLD, constraints);
        vertex1TextField.setEnabled(false);    
        vertex1Panel.add(vertex1TextField,constraints);
        vertex2TextField.setEnabled(false);
//        vertex2TextField.addPropertyChangeListener("value", this );
        vertex2Panel.add(vertex2TextField,constraints );
        distTextField.setEnabled(false);
//        distTextField.addPropertyChangeListener("value", this );
        distancePanel.add(distTextField, constraints); 
        
        
        addEdgesBTNPanel.add(addEdgesBTN, constraints);
        
        //add each button to the respective panels
        constraints.gridx = 3;
        constraints.insets = new Insets(0,10,0,0);
        constraints.weightx = 0.0;
        inputEdgesPanel.add(defineGraphBTN, constraints);  
        
        addEdgesBTNPanel.add(addEdgesBTN, constraints);
       
        edgesCollectionTxtArea.setFont(normalTextFont.deriveFont(14));
        edgesCollectionPanel.add(edgesCollectionTxtArea, constraints);
        
        graphLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        graphLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Add components to the mainPanel1      
        Component[] mainPanel1Components = {graphLabel, inputEdgesPanel, vertex1Panel, vertex2Panel, distancePanel, addEdgesBTNPanel, edgesCollectionTxtArea};
        addComponents(mainPanel1, graphLabel, mainPanel1Components);

    }
    //create and organize components on the right-side of the frame for Solution
    public void createMainPanel2(){
        
        JPanel buttonsPanel;
        GridBagConstraints constraints;
        
        solutionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 130, 0));
        
        constraints = createConstraints(false, 2, 0, 1, 1);
        buttonsPanel = new DarkPanel(this); 
        buttonsPanel.setLayout(new FlowLayout());
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridy = 1;
       
        buttonsPanel.add(solutionBTN, constraints);
        
        constraints.gridx = 3;
        constraints.insets = new Insets(10,5,0,0);       
        buttonsPanel.add(newGraphBTN, FlowLayout.CENTER);
        
        solutionTxtArea.setRows(12);
        //call the addComponents method to add Components to the mainPanel2
        Component[] mainPanel2Components = { solutionLabel,solutionTxtArea,  buttonsPanel };        
        addComponents(mainPanel2, solutionLabel, mainPanel2Components); 
       
        
    }
    //create the main panels and add its components onto it
    public void addComponents(JPanel mainPanel, JLabel panelsLabel, Component[] components){
        //components constraints
        GridBagConstraints constraints = createConstraints(true, 0, 0, 1, 1);
        
        //reset insets for the rest of the components
        constraints.insets = new Insets(0,0,10,20);    
//        constraints.gridwidth = 1;
       constraints.weightx = 0.5;
//         constraints.fill = GridBagConstraints.HORIZONTAL;
        //Add components to the main panels   
        for( Component component : components){
            constraints.gridy += 1;
            mainPanel.add(component, constraints);
        }
    }
   
    public void addKeyListener(JComponent[] components, boolean addKeyListener, boolean addActionListener){
        
        if(addKeyListener == true){
            for(JComponent component: components){
                component.addKeyListener(this);
            }
        }
    }
    
    public GridBagConstraints createConstraints(boolean shouldFill, int gridX, int gridY, int gridWeight, int gridHeight ){
        GridBagConstraints c = new GridBagConstraints();
        
        if(shouldFill){
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        else{
            c.fill = GridBagConstraints.NONE;
        }
        
        c.ipadx = 0;
        c.gridx = gridX;
        c.gridy = gridY;
        c.gridwidth = gridWeight;
        c.gridheight = gridHeight;
        
        return c;
    }   
    public Color getTransparentColor(Color color){
        int red = color.getRed();
        int blue = color.getBlue();
        int green = color.getGreen();
        int alpha = (int)(color.getAlpha()*0.5);
        
       Color transparentColor = new Color(red, green, blue, alpha);
       
        return transparentColor;
    }

    @Override
    public void focusGained(FocusEvent e) {
       
    }

    @Override
    public void focusLost(FocusEvent e) {
       
    }
    //actionPerformed for defineGraphBTn
    public void defineGraphBTNActionPerformed() {
         
        x = 0;
        defineGraphBTNClickCounter++;
          
        //check if the number of vertex inputted is a digit
        if(!inputEdgesTxtFLD.getText().matches("[1-9]{1,20}")) {
            JOptionPane.showMessageDialog(null, "Enter digit only and greater than 0", "Input Error", JOptionPane.ERROR_MESSAGE);
            inputEdgesTxtFLD.requestFocus();
           
        }
        else{
             //enable vertex1TextField and requestFocus
            enableDisableComponents(null,vertex1TextField,true);
            edgeCount = Integer.parseInt(inputEdgesTxtFLD.getText());
            
            //determining whether the input in inputEdgesTxtFLD has changed by checking the number of clicks of defineGraphBTN                     
            if(defineGraphBTNClickCounter > 1 ){
                
                edgesCollectionTxtArea.setText(textAreaTitles[0]);
                
                //reinitialize the array EDGES
                 EDGES = new Graph.Edge [edgeCount];
                 
                 //reset elements for the vertex choices for the solution
                 resetComponents(); 
                 vertex1TextField.requestFocus();
            }
            else {
                EDGES = new Graph.Edge [edgeCount];
            }
         
        }
    } 
   
    //actionPerformed for addEdgesBTN
    public void addEdgesBTNActionPerformed() {
       solutionBTN.requestFocus();
       newGraphBTN.setEnabled(true);
       
        showGraph();
    } 
    
    public void showGraph(){
         
        //get inputs from vertex1TextField, vertex2TextField and distTextField;
        vertex1Input = vertex1TextField.getText();
        vertex2Input = vertex2TextField.getText();
        distanceInput = Integer.parseInt(distTextField.getText());
        EDGES[x] = new Graph.Edge(vertex1Input,vertex2Input, distanceInput );
         x+=1;
          
         //collect the start vertices
         if( !vertex1Array.contains(vertex1Input))vertex1Array.add(vertex1Input);
         
        //collect the start vertices
        if( !vertex2Array.contains(vertex2Input))vertex2Array.add(vertex2Input);
        
         //display the entered data for the Graph
        edgesCollectionTxtArea.append(Graph.string2);       
        
        //disable components
        JComponent[] components = {addEdgesBTN,vertex1TextField,vertex2TextField, distTextField};
        enableDisableComponents(components,null,false);
        
        if (edgeCount == x){
        
            //enable solutionTxtArea, and solutionBTN and request focus
            JComponent[] com = {solutionBTN, solutionTxtArea};
            enableDisableComponents(com,null, true);
        }  
        else{
            vertex1TextField.requestFocus();
            vertex1TextField.setText("e.g. Venice");
            vertex2TextField.setText("e.g. Milan");
            distTextField.setText("e.g. 10");
        }
    }
     //actionPerformed for solutionBTN
    public void solutionBTNActionPerformed(){
        
        GRAPH = new Graph(EDGES);
        GRAPH.graph.keySet();
        START = (String)JOptionPane.showInputDialog(null, "Choose START vertex", 
            "Input", JOptionPane.QUESTION_MESSAGE, null,vertex1Array.toArray(),vertex1Array.get(0)  );
        //remove the START vertex from the END Vertex Options
        vertex2Array.remove(START);
        END = (String)JOptionPane.showInputDialog(null, "Choose END vertex", 
            "Input", JOptionPane.QUESTION_MESSAGE, null,vertex2Array.toArray(),vertex1Array.get(0)  );
        GRAPH.dijkstra(START);
        GRAPH.printPath(END);
        GRAPH.graph.get(END);
        
        //display Paths on the solutionTextArea
        solutionTxtArea.setWrapStyleWord(true);
        solutionTxtArea.append(Graph.string1);
        
        //enable newGraphBTN and requestFocus
         enableDisableComponents(null, newGraphBTN, true);
        //clear the Graph.string1 to resetComponents the path output in the SolutionTextArea
        Graph.string1 = "\n";
    }
    //actionPerformed for newGraphBTN
    public void newGraphBTNActionPerformed(){
        //call resetComponents method
        resetComponents();
        inputEdgesTxtFLD.setEnabled(true);
        inputEdgesTxtFLD.requestFocus();
    }
    //method to reset components after newGraphBTN actionPerformed
    public void resetComponents(){
        x = 0;
        
        //reset the components: enable inputEdgesTxtFLD and requestFocus
      
        edgesCollectionTxtArea.setAlignmentX(SwingConstants.CENTER);
        edgesCollectionTxtArea.setText(textAreaTitles[0]);
        solutionTxtArea.setText(textAreaTitles[1]);
        
        //disable the TextAreas and the Solution Button
        JComponent[] com = {edgesCollectionTxtArea, solutionTxtArea, solutionBTN};
        enableDisableComponents(com,null, false);
        
        //reset JTextFields for new Graph
        resetTextFields();
        
        //delete all elements in the vertex1Array and vertex2Array 
        vertex1Array.clear();
        vertex2Array.clear();
    }
    
    // method to disable and reset the text of the Textfields
    public void resetTextFields(){
        JTextField[] fields = {vertex1TextField, vertex2TextField, distTextField };
        String[] texts = {"e.g. Venice","e.g. Milan", "e.g. 10"};
        int i =0;
        
        for(JTextField field: fields){
            field.setEnabled(false);
            field.setFont(promptTextFont);
            field.setText(texts[i]);
            i++;
        }
    }
   
    public void enableDisableComponents(JComponent[] components,JComponent comp, boolean enable){
        
       if(components != null){
             for(JComponent component: components){
                component.setEnabled(enable);
            }
       }
       else{
           comp.setEnabled(enable);
           if(enable == true){
               comp.requestFocus();
           }
       }
    }
    public void enableError(JTextComponent source, String errorText){        
        //set border to red color for error prompt
        source.setBorder(BorderFactory.createLineBorder(ERROR_COLOR, 2));     
        /*
        *TODO: SHOW ERROR LABELS
        */
        JLabel errorLabel = new JLabel(errorText);
        errorLabel.setLabelFor(source);
        errorLabel.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) { 
        Object source = e.getSource();
        actionPerformedJComponents((JComponent) source, null,e );
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        Object source = e.getSource();        
        actionPerformedJComponents((JComponent) source, e,null );
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    
    public boolean actionPerformedJComponents(JComponent source, KeyEvent ke, ActionEvent ae ){
        
          boolean hasEnteredData, hasEnteredDigit;
          
          //get index of the source
          int index = sources.indexOf(source);
          JComponent nextComponent = sources.get(index+1);
          
          //check if the user input for number of edges and distance fields are digits
        if((source == inputEdgesTxtFLD|| source == distTextField || source == vertex1TextField || source == vertex2TextField) && ke.getKeyCode() == KeyEvent.VK_ENTER ){
            JTextComponent currTxtFLD = (JTextComponent)source;
            
            hasEnteredData = !(currTxtFLD.getText().isEmpty());
            hasEnteredDigit = ((JTextComponent)source).getText().matches("[0-9]{1,20}");
            
            //check if source is inputEdgesTxtFLD or distTextField, then check if user has entered data and if this data is digit
            if(hasEnteredData &&  !hasEnteredDigit && (source == inputEdgesTxtFLD|| source == distTextField) || !hasEnteredData){
                /*
                *TODO: SHOW ERROR TEXT MESSAGE
                */
                //enable error message
                 enableError(currTxtFLD, "enter numbers only" );
            }
            //check if source is vertex1TextField or vertex2TextField, then check if user has entered data and if vertex2 is not equals to vertex1
            else if(hasEnteredData && ( source == vertex1TextField || source == vertex2TextField) 
                    && ( vertex1TextField.getText().equalsIgnoreCase(vertex2TextField.getText()))|| !hasEnteredData){
                   /*
                *TODO: SHOW ERROR TEXT MESSAGE
                */
                //enable error message
                enableError(currTxtFLD, "start should not be equal to end" );
            }
            else{
                //enable next component
                enableDisableComponents(null, nextComponent, true);
                if(source == distTextField){
                }
            }
        }
        if((source == defineGraphBTN || source == addEdgesBTN || source == solutionBTN || source == newGraphBTN   ) ){
            if(defineGraphBTN == source  ){
                defineGraphBTNActionPerformed();
            }
            else if(source == addEdgesBTN){
                addEdgesBTNActionPerformed();
            }
            else if(source == solutionBTN){
                solutionBTNActionPerformed();
            }
            else if(source == newGraphBTN){
                newGraphBTNActionPerformed();
            }
        }
        return nextComponent.isEnabled();
    }
  
    
    
     public static void main(String[] args) throws FileNotFoundException{
        EventQueue.invokeLater( DjikstraVersion4::new) ;
    }
   
}
