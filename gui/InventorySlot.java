package gui;

import entities.Item;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 * Décrivez votre classe InventoryItem ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class InventorySlot extends JButton
{
    public static int SLOT_SIZE = 32;
    public static int SLOT_SPACE = 36;
    
    private InventoryPanel aPanel;
    private Item aItem;
    private int index;
    
    /**
     * Constructeur d'objets de classe InventoryItem
     */
    public InventorySlot(final InventoryPanel pPanel, final ItemInfoPanel iPanel, final int index, final int pX, final int pY)
    {
        super();
        
        this.index = index;
        this.aPanel = pPanel;
        this.setBounds(pX, pY, SLOT_SIZE, SLOT_SIZE);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.addActionListener(this.aPanel.getListener());
        this.addMouseListener(new SlotEventListener(this, iPanel, pPanel));
    }
    
    public int getIndex(){ return this.index; }
    
    public Item getItem(){ return this.aItem; }
    public void setItem(final Item pItem) { 
        this.aItem = pItem;
        if(pItem!=null)this.setIcon(pItem.getTexture());
        else this.setIcon(null);
    }
    public boolean hasItem(){ return this.aItem != null; }
    
    public InventoryPanel getPanel(){ return this.aPanel; }
}
