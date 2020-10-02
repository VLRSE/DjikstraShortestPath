
package Class;
        

import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
/**
 *
 * @author 
 */
public class CustomDialogBox extends JDialog implements ActionListener, PropertyChangeListener{
    
    private String userInput;
    private JTextField textField;
    private JOptionPane optionPane;
    private String btnStringOK = "OK";
    private String btnStringCancel = "Cancel";
    private Object[] inputChoices;
    private String btnString1 = "OK";
    private String btnString2 = "Cancel";
    
    
    /*
    *Create customized dialog box
    */
    public CustomDialogBox(JFrame frame, String message, Object[] inputs){
        super(frame, true);
        
        setTitle("User Input");
        
            //create a JOptionPane object with choices
           optionPane = new JOptionPane​(message, JOptionPane​.QUESTION_MESSAGE, JOptionPane​.OK_CANCEL_OPTION
                   , null, inputs, inputs[0]);
        
        
        setContentPane(optionPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e){
                    optionPane.setValue(JOptionPane.CLOSED_OPTION);
                }
        });
        
        //textField to get first focus
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent ce){
                   textField.requestFocusInWindow();
            }
            
        });
        
        //register  an event  handler that puts  the text into  the option pane
        textField.addActionListener(this);
        
        //register an event handler that reacts to option pane´s state changes
        optionPane.addPropertyChangeListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
    
}
