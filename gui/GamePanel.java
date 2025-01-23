package gui;

import engine.GameEngine;
import engine.Block;
import engine.WorldEventsListener;
import entities.Player;
import entities.Entity;
import java.util.HashMap;

import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLayeredPane;

import entities.EntityConstant;

/**
 * Décrivez votre classe GuiPanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GamePanel extends JLayeredPane 
{
    private InterfacePanel aInterfaces;
    private GameEngine aEngine;
    private WorldPanel aWorldPanel;
    private HashMap<EntityConstant, Bar> aBars;

    public GamePanel(GameEngine pEngine, UserInterface pGUI){
        this.aEngine = pEngine;
        this.aBars = new HashMap<>();
        
        this.setBackground(new Color(0, 0, 255));
        this.setLayout(null);
        this.aWorldPanel = new WorldPanel(pEngine, pGUI);
        //Moved to WorldBuilder.
        //createBlocks(-48);
        //createBlocks(96);
        this.add(this.aWorldPanel, DEFAULT_LAYER);
        this.setOpaque(true);
        this.aInterfaces = new InterfacePanel(pEngine, pGUI);
        this.add(this.aInterfaces, POPUP_LAYER);
        
        this.setVisible(true);
    }
    
    public void reset(){
        
    }
    
    public HashMap<EntityConstant, Bar> getBars(){ return this.aBars; }
    
    public void createBars(Player player){
        this.aBars.put(player.getLife(), new Bar(Icons.LIFE, player.getLife(), 0));
        this.aBars.put(player.getHunger(), new Bar(Icons.HUNGER, player.getHunger(), 1));
        
        this.add(this.aBars.get(player.getLife()), MODAL_LAYER);
        this.add(this.aBars.get(player.getHunger()), MODAL_LAYER);
    }
    
    public void updateSkyColor(){
        int time = this.aEngine.getWorld().getTime();
        int B = 0;
        
        if(time<1200)      B = (int)(time*0.01);
        else if(time<2400) B = (int)((time-1200)*0.2 +12);
        else if(time<3600) B = 252;
        else if(time<4800) B = (int)((time-3600)*(-0.2) +252);
        else               B = (int)((time-4800)*(-0.01)+12);
        int rg = B/4; //To add some white to the sky's color.
        this.setBackground(new Color(rg, rg, B));
    }
    
    //Number of blocks. Positive means right and negatives means left.
    public void createBlocks(final int number){
        this.aWorldPanel.createBlocks(number);
    }
    
    public void updatePlayerPos(Player player){
        if(getScreenPosition(player) < (this.getWidth()/4)) this.aWorldPanel.addX(2);
        else if(getScreenPosition(player) > (this.getWidth()/4)*3) this.aWorldPanel.addX(-2);
        
        if(getScreenPositionY(player) < (this.getHeight()/4)) this.aWorldPanel.addY(2);
        else if(getScreenPositionY(player) > (this.getHeight()/4)*3) this.aWorldPanel.addY(-2);
    }
    
    public int getScreenPosition(Player player) {
        return player.getX() + this.aWorldPanel.getX();
    }
    
    public int getScreenPositionY(Player player) {
        return player.getY() + this.aWorldPanel.getY();
    }
    
    public WorldPanel getWorldPanel(){ return this.aWorldPanel; }
    
    public InterfacePanel getInterfaces(){ return this.aInterfaces; }
    
    public InventoryPanel getInventoryGUI(){ return this.aInterfaces.getInventory(); }
    
    public Chat getChat(){ return this.aInterfaces.getChat(); }
    
    public List<Block> getBlocks(){ return this.aWorldPanel.getBlocks(); }
    public WorldEventsListener getEventListener(){ return this.aWorldPanel.getEventListener(); }
    
    public void addEntity(final Entity entity){
        this.aWorldPanel.add(entity, MODAL_LAYER);    
    }
    
}
