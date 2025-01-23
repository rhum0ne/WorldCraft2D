package gui;

import engine.GameEngine;
import key.Keys;
import key.KeyboardListener;
import engine.Block;
import engine.WorldEventsListener;
import entities.Player;
import entities.EntityConstant;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.sound.sampled.*;

import engine.Sounds;
/**
 * Décrivez votre classe UserInterface ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class UserInterface
{
    private GameEngine aEngine;
    
    private GameMenuPanel gameMenu;
    private final GuiFrame aFrame;
    private GamePanel aPanel;
    private InventoryPanel inventory;
    private Chat aChat;
    
    private SoundsManager aSoundsManager;
    
    private KeyboardListener aKB;
    
    /**
     * Constructeur d'objets de classe UserInterface
     */
    public UserInterface(final GameEngine pEngine)
    {
        this.aEngine = pEngine;
        
        this.aFrame = new GuiFrame(pEngine);
        this.gameMenu = new GameMenuPanel(pEngine, this);
        this.aFrame.addComponentListener(new ResizeEvent(this));
        
        this.aSoundsManager = new SoundsManager();
        this.aKB = new KeyboardListener(pEngine);
        this.aFrame.addKeyListener(this.aKB);
        this.setPlaying(false);
    }
    
    /**
     * Plays the specified sound.
     * 
     * @param pSound The sound to play.
     */
    public void playSound(final Sounds pSound){ 
        Clip vClip = this.aSoundsManager.get(pSound);
        if(vClip == null) return;

        vClip.stop();
        vClip.setMicrosecondPosition(0);
        vClip.start();
    }
    
    public void repaint(){
        this.aPanel.repaint();
    }
    
    public void resetWorld(){
        this.aPanel.getWorldPanel().removeAll();
        this.aPanel.getWorldPanel().getBlocks().clear();
        repaint();
    }
    
    public void setPlaying(boolean state){
        if(state){
            if(this.aPanel != null)
                showGame();
            else createGame();
        }
        else showMainMenu();
    }
    
    private void createGame(){
        this.aPanel = new GamePanel(this.aEngine, this);
        this.aChat = this.aPanel.getChat();
        this.inventory = this.aPanel.getInventoryGUI();
        
        this.showGame();
    }
    
    private void showGame(){
        if(this.gameMenu != null)
            this.aFrame.remove(this.gameMenu);
        this.aFrame.add(this.aPanel);
        this.aFrame.setVisible(true);
        
        this.aPanel.getInterfaces().replace();
    }
    
    private void showMainMenu(){
        if(this.aPanel != null)
            this.aFrame.remove(this.aPanel);
        this.aFrame.add(this.gameMenu);
        this.aFrame.setVisible(true);
    }
    
    public void createBars(Player player){ this.aPanel.createBars(player); }
    
    public void updateSkyColor(){
        this.aPanel.updateSkyColor();
    }
    
    public HashMap<EntityConstant, Bar> getBars(){ return this.aPanel.getBars(); }
    
    public void showInventory(){ 
        this.inventory.setVisible(true);
        this.inventory.requestFocus();
    }
    
    public void closeInventory(){
        this.inventory.setVisible(false);
        this.aFrame.requestFocus();
    }
    
    public GameMenuPanel getMenuPanel(){ return this.gameMenu;}
    
    public void showMessage(final String message){ this.aChat.showMessage(message); }
    
    public void setShowingChat(final boolean pState){
        this.aChat.setVisible(pState);
        if(pState) this.aChat.write();
        else this.aFrame.requestFocus();
    }
    
    public void requestGameFocus(){
        this.aFrame.requestFocus();
    }
    
    public boolean isChatVisible(){ return this.aChat.isVisible(); }
    
    public List<Block> getBlocks() { return this.aPanel.getBlocks(); }
    
    public GamePanel getGamePanel(){
        return this.aPanel;
    }
    
    public GuiFrame getFrame(){
        return this.aFrame;
    }
    
    public GameEngine getEngine(){
        return this.aEngine;
    }
    
    public void updateLocation(Player player){
        this.aPanel.updatePlayerPos(player);
    }
    
    public WorldEventsListener getWorldEventsListener(){ return this.aPanel.getEventListener(); }
    
    public void updateInventory(){
        this.inventory.updateSlots();
    }
}
