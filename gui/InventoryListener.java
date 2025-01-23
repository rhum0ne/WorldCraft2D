package gui;

import engine.GameEngine;
import javax.swing.*;
import java.awt.event.*;
/**
 * Décrivez votre classe InventoryListener ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class InventoryListener implements ActionListener
{
    private GameEngine aEngine;
    private InventoryPanel   aPanel;
    
    /**
     * Constructor for the InventoryListener class.
     *
     * @param pPanel  The GuiPanel associated with the listener.
     * @param pEngine The GameEngine associated with the listener.
     */
    public InventoryListener(final InventoryPanel pPanel, final GameEngine pEngine){
        this.aPanel = pPanel;
        this.aEngine = pEngine;
    }
    
    /**
     * Actionlistener interface for entry textfield.
     * 
     * @param pE The ActionEvent that triggered the action.
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        if(pE.getSource() instanceof JButton){
            JButton vButton = (JButton) pE.getSource();
            if(vButton.getActionCommand().equals("close")){
                aPanel.getGUI().closeInventory();
            }
        }
    }
}
