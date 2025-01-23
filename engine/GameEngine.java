package engine;

import java.util.Timer;
import java.util.List;
import java.util.ArrayList;

import entities.EntitiesManager;
import entities.Player;
import entities.Pig;
import gui.UserInterface;
import entities.Item;
import commands.CommandsManager;
import commands.EnteredCommand;
import entities.Entities;
import entities.Entity;
import key.Keys;
/**
 * Décrivez votre classe GameEngine ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GameEngine
{
    private static GameEngine INSTANCE;
    
    public boolean inGame = false;
    
    private UserInterface aGUI;
    private World aWorld;
    private Player aPlayer;
    
    private List<Craft> crafts;
    
    private EntitiesManager aEntitiesManager;
    private GameTask aTask;
    private Timer aTimer;
    private CommandsManager commandsManager;
    
    public GameEngine(){
        INSTANCE = this;
        
        Material.load();
        Entities.loadTextures();
        this.aEntitiesManager = new EntitiesManager();
        this.commandsManager = new CommandsManager(this);
        this.crafts = new ArrayList<>();
        Keys.createKeys();
        Structures.createStructures();
    }
    
    public static GameEngine getInstance(){ return INSTANCE; }
    
    private void createCrafts(){
        this.crafts.add(new Craft(new Material[]{
            null, null, null, 
            null, Material.WOOD, null, 
            null, null, null}, new Item(Material.PLANKS, 8, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, 
            Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, 
            Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE}, new Item(Material.BASALT, 6, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            null, null, null, 
            null, Material.COBBLESTONE, Material.COBBLESTONE, 
            null, Material.COBBLESTONE, Material.COBBLESTONE}, new Item(Material.STONE_BRICKS, 4, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            null, Material.COBBLESTONE, null, 
            Material.COBBLESTONE, null, Material.COBBLESTONE, 
            null, Material.COBBLESTONE, Material.COBBLESTONE}, new Item(Material.CHISELED_STONE, 4, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            null, null, null, 
            null, Material.CALCITE, Material.CALCITE, 
            null, Material.CALCITE, Material.CALCITE}, new Item(Material.CALCITE_BRICKS, 4, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            null, Material.CALCITE, null, 
            Material.CALCITE, null, Material.CALCITE, 
            null, Material.CALCITE, null}, new Item(Material.CHISELED_CALCITE, 4, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            null, null, null, 
            null, Material.PLANKS, Material.PLANKS, 
            null, Material.PLANKS, Material.PLANKS}, new Item(Material.TRAP, 2, this, new Location())));
        this.crafts.add(new Craft(new Material[]{
            Material.PLANKS, Material.PLANKS, Material.PLANKS, 
            Material.PLANKS, Material.PLANKS, Material.PLANKS, 
            Material.PLANKS, Material.PLANKS, Material.PLANKS}, new Item(Material.DARK_PLANKS, 9, this, new Location())));
    }
    
    public void setGUI(final UserInterface pGUI){
        this.aGUI = pGUI;
    }
    
    public void play(String worldName, Seed seed){
        this.aWorld = new World(this, worldName, seed);
        this.play();
    }
    
    public void play(String worldName){
        this.aWorld = new World(this, worldName, Seed.random());
        this.play();
    }
    
    private void play(){
        this.aWorld.spawn(this.aPlayer = new Player(this));
        this.aWorld.loadWorld();
        
        this.aGUI.createBars(this.aPlayer);
        
        this.aTimer = new Timer();
        this.aTask = new GameTask(this);
        
        aTimer.schedule(this.aTask, 0, 20);
        createCrafts();
        
        this.inGame = true;
    }
    
    public boolean isInGame(){ return this.inGame; } 
    
    public Player getPlayer(){ return this.aPlayer; }
    
    public void reset() { 
        this.inGame = false;
        
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        
        this.aTimer.cancel();
        this.aWorld.stop();
        this.aTimer = null;
        this.aTask = null;
        this.crafts.clear();
        this.aPlayer = null;
        this.aGUI.resetWorld();
        this.aEntitiesManager.reset();
        this.aWorld = null;
        
        this.aGUI.repaint();
    }
    
    public List<Craft> getCrafts(){ return this.crafts; }
    
    public void interpretMessage(final String msg){
        
        if(msg.startsWith("/")){
            String cmd = msg.substring(1);
            EnteredCommand vCommand = new EnteredCommand(cmd, this.getPlayer());
            
            if(this.commandsManager.isCommand(vCommand)){
                this.commandsManager.executeCommand(vCommand);
            }
            else this.aPlayer.sendMessage("Unknown command.");
        }
        
        else this.aGUI.showMessage("<" + this.aPlayer.getName() + "> " + msg);
    }
    
    public void delete(Object obj){
        obj = null;
    }
    
    public World getWorld(){ return this.aWorld; }
    public UserInterface getGUI(){ return this.aGUI; }
    public EntitiesManager getEntitiesManager(){ return this.aEntitiesManager; }
}
