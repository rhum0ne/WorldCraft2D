package engine;

import java.util.HashMap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

import entities.Entity;
import entities.Item;
/**
 * Décrivez votre classe World ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class World
{
    static String separator = ";";
    
    private GameEngine aEngine;
    private HashMap<Location, Block> blocks;
    
    private String worldName;
    private Seed seed;
    
    private List<Item> aItems;
    
    private int time;
    
    private WorldBuilder generator;
    private WorldTask task;
    private Timer aTimer;
    
    public World(GameEngine pEngine, String worldName, Seed seed){
        this.worldName = worldName;
        this.seed = seed;
        
        this.blocks = new HashMap<>();
        this.aItems = new ArrayList<>();
        this.aEngine = pEngine;
        
        this.generator = new WorldBuilder(this, pEngine);
    
        this.task = new WorldTask(pEngine, this);
        this.aTimer = new Timer();
        aTimer.schedule(this.task, 0, 40);
    }
    
    public Seed getSeed(){ return this.seed; }
    
    public void save()throws IOException {
        createSaveDir(worldName);
        saveBlocks();
        saveData();
    }
    
    private void saveData()throws IOException {
        File save = new File("saves\\" + this.worldName + "\\world.txt");
        save.createNewFile();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(save, false));
        writer.append(this.worldName);
        writer.append("\n");
        writer.append(this.seed.toString());
        
        writer.close();
    }
    
    private void saveBlocks()throws IOException {
        File save = new File("saves\\" + this.worldName + "\\block.txt");
        save.createNewFile();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(save, false));
        for(Location loc : this.blocks.keySet()){
            Block bloc = blocks.get(loc);
            if(bloc.getType() == Material.AIR) continue;
            String x = String.valueOf((int)loc.getX());
            String y = String.valueOf((int)loc.getY());
            writer.append(x).append(separator).append(y).append(separator);
            writer.append(bloc.getType().toString());
            writer.append("\n");
        }
        
        writer.close();
    }
    
    private void createSaveDir(String worldName){
        File saves = new File("saves\\"+worldName);
        saves.mkdirs();
    }
    
    public void addTime(int a){
        //Pour un cycle jour/nuit de 20min mettre 60000 ticks.
        time+=a;
        time%=6000;
        this.aEngine.getGUI().updateSkyColor();
    }
    public int getTime(){ return this.time; }
    
    public HashMap<Location,Block> getBlocks(){ return this.blocks; }
    
    public void stop(){ 
        this.aTimer.cancel(); 
        this.aTimer = null; 
        this.task = null;
    }
    
    public void loadWorld(){
        createSaveDir(worldName);
        File save = new File("saves\\" + this.worldName + "\\block.txt");
        try
        {
            if(save.createNewFile())this.generator.create();
            else {
                this.generator.load(save);
                this.loadData();
            }
            
            this.save();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void loadData(){
        File seed = new File("saves\\" + this.worldName + "\\world.txt");
        try { // pour "essayer" les instructions suivantes :
            Scanner vSc = new Scanner( seed );
            vSc.nextLine();
            
            if(this.seed == null){
                String enteredSeed = vSc.nextLine();
                this.seed = Seed.create(enteredSeed);
            }
        } catch ( final FileNotFoundException pFNFE ) {
            System.out.println("Error: file " + seed.getName() + " not found. Creating new data...");
            this.seed = Seed.random();
        }
    }
    
    public void spawn(Structures struc, Location loc){
        struc.getStructure().build(this, loc);
    }
    
    public void spawn(Entity entity){
        if(entity instanceof Item) this.aItems.add((Item) entity);
        this.aEngine.getGUI().getGamePanel().addEntity(entity);
    }
    
    public void remove(Entity entity){
        this.aItems.remove(entity);
        this.aEngine.getGUI().getGamePanel().getWorldPanel().remove(entity);
    }
    
    public Block getBlockAt(final int pX, final int pY){
        for(Location loc : this.blocks.keySet()){
            if(loc.getX() == pX && loc.getY() == pY){
                return this.blocks.get(loc);
            }
        }
        return null;
    }
    
    public Block getBlockAt(Location loc){
        if(this.blocks.containsKey(loc)) return blocks.get(loc);
        return this.getBlockAt((int)loc.getX(), (int)loc.getY());
    }
}
