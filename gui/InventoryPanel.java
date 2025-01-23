package gui;

import engine.GameEngine;
import entities.Item;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.net.URL;
/**
 * Décrivez votre classe InventoryPanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class InventoryPanel extends JPanel
{
    
    private final InventoryListener aListener;
    private final UserInterface aGUI;
    private final GameEngine aEngine;
    private final List<InventorySlot> aSlots;
    private Item selectedItem;
    private ItemInfoPanel aIPanel;
    
    private CraftingGrid craftingGrid;
    
    /**
     * Constructeur d'objets de classe InventoryPanel
     */
    public InventoryPanel(final UserInterface pGUI, final ItemInfoPanel iPanel, final GameEngine pEngine)
    {
        super();
        
        this.aSlots = new ArrayList<>();
        this.aGUI = pGUI;
        this.aIPanel = iPanel;
        this.aEngine = pEngine;
        this.setLayout(null);
        this.setBounds(250, 100, 461, 277);
        this.setSize(new Dimension(461, 277));
        this.aListener = new InventoryListener(this, pEngine);
        this.setBackground(new Color(0, 0, 0, 0));
        
        this.setVisible(false);
        createCloseButton();
        createSlots(iPanel);
        createCraftingGrid();
    }
    
    private void createCraftingGrid(){
        this.craftingGrid = new CraftingGrid(9, 266, 69, this, this.aIPanel, this.aEngine);
    }

    public void setSelectedItem(final Item pItem){
        this.selectedItem = pItem;
    }
    
    public boolean hasSelectedItem(){ return this.selectedItem != null; }
    
    public Item getSelectedItem(){ return this.selectedItem; }
    
    public GameEngine getEngine(){ return this.aEngine; }
    
    public void place(){
        int h = this.getParent().getHeight();
        int w = this.getParent().getWidth();

        this.setLocation((w - this.getWidth())/2, (h - this.getHeight())/2);
    }
    
    private void createCloseButton(){
        JButton vClose = new JButton();
        vClose.setActionCommand("close");
        vClose.setBounds(417, 7, 25, 25);
        vClose.setBorder(BorderFactory.createEmptyBorder());
        vClose.setContentAreaFilled(false);
        vClose.addActionListener(this.getListener());
        String vImagePath = "assets\\gui\\cross.png"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            vClose.setIcon( vIcon );
        }
        this.add(vClose);
    }
    
    private void createSlots(final ItemInfoPanel iPanel){
        int vXStart = 16;
        int vYStart = 35;
        for(int i=0; i<36; i++){
            InventorySlot vSlot = new InventorySlot(this, iPanel, i, vXStart + (i%6)*InventorySlot.SLOT_SPACE, vYStart + (i/6)*InventorySlot.SLOT_SPACE);
            aSlots.add(vSlot);
            this.add(vSlot);
        }
    }
    
    public void updateSlots(){
        for(int i=0; i<36; i++){
            Item vItem = null;
            if(i<aEngine.getPlayer().getInventory().getItems().size()) vItem = this.aEngine.getPlayer().getInventory().getItems().get(i);
            
            this.aSlots.get(i).setItem(vItem);
        }
    }
    
    public InventoryListener getListener(){
        return this.aListener;
    }
    
    public UserInterface getGUI(){
        return this.aGUI;
    }
    
    /**
     * Custom painting method to draw the background image on the panel.
     *
     * @param pGraph The graphics context to paint.
     */
    @Override
    public void paintComponent(Graphics pGraph){
        Image icon;
        try{
            icon = ImageIO.read(new File("assets\\gui\\inventory.png"));
        } catch(IOException e){
            System.out.println(e);
            return;
        }
        super.paintComponent(pGraph);
        
        pGraph.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}