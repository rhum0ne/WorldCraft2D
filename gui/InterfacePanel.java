package gui;

import engine.GameEngine;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Décrivez votre classe InterfacePanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class InterfacePanel extends JPanel
{
    private Chat aChat;
    private InventoryPanel aInventory;
    private ItemInfoPanel aIPanel;
    
    public InterfacePanel(GameEngine pEngine, UserInterface pGUI){
        this.setLayout(null);
        this.setBounds(0, 0, 1200, 600);
        this.setOpaque(false);
        this.aChat = new Chat(pEngine);
        this.add(this.aChat);
        this.aIPanel = new ItemInfoPanel();
        this.add(aIPanel);
        this.aInventory = new InventoryPanel(pGUI, aIPanel, pEngine);
        this.add(this.aInventory);
        
        this.setVisible(true);
    }
    
    public void replace(){
        int w = this.getParent().getParent().getWidth();
        int h = this.getParent().getParent().getHeight();
        this.setBounds(0, 0, w, h);
        this.aInventory.place();
        this.aChat.place();
    }
    
    public Chat getChat(){ return this.aChat; }
    public InventoryPanel getInventory(){ return this.aInventory; }
}
