package gui;

import engine.GameEngine;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Décrivez votre classe Chat ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Chat extends JPanel implements ActionListener
{
    private GameEngine aEngine;
    
    private JTextField aEntryField;
    private JTextArea aTextArea;
    
    public Chat(final GameEngine pEngine){
        this.aEngine = pEngine;
        this.setBackground(new Color(0, 0, 0, 50));
        this.setBounds(3, 200, 400, 330);
        this.setLayout(null);
        
        this.aEntryField = new JTextField();
        this.aEntryField.setBounds(0, 310, 400, 20);
        this.aEntryField.addActionListener(this);
        this.add(this.aEntryField);
        
        this.aTextArea = new JTextArea();
        this.aTextArea.setEditable(false);
        JScrollPane vListScroller = new JScrollPane( this.aTextArea );
        vListScroller.setPreferredSize( new Dimension(400, 250) );
        vListScroller.setMinimumSize( new Dimension(400, 250) );
        vListScroller.setBounds(0, 0, 400, 250);
        vListScroller.setBorder(null);
        this.add(vListScroller);
        
        this.setVisible(false);
    }
    
    public void place(){
        int h = this.getParent().getHeight();

        this.setLocation(this.getX(), h - this.getHeight()-5);
    }
    
    public void showMessage(final String message){
        this.aTextArea.append("\n");
        this.aTextArea.append(message);
        this.aTextArea.setCaretPosition( this.aTextArea.getDocument().getLength() );
    }
    
    public void write(){ 
        this.aTextArea.setVisible(true);
        this.aEntryField.requestFocus(); 
    }
    
    public void actionPerformed(ActionEvent e){
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        if(!vInput.replaceAll(" ", "").equals("")) this.aEngine.interpretMessage( vInput );
        this.aEngine.getGUI().setShowingChat(false);
    }
}
