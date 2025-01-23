package engine;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.*;
import java.net.URL;
import entities.Item;

/**
 * Décrivez votre classe Block ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Block extends JButton
{
    private static Color TRANSPARENT = new Color(0, 0, 0, 0);
    
    private GameEngine aEngine;
    
    private Location aLocation;
    private Material aMaterial;
    private int tick;
    
    public Block(GameEngine pEngine, WorldEventsListener pListener, final int pX, final int pY){
        this.aLocation = new Location(pX, pY);
        this.aMaterial = Material.AIR;
        this.aEngine = pEngine;
        //this.setBorder(BorderFactory.createEmptyBorder());
        this.setBorderPainted(false); 
        this.setBounds(pX*32, pY*32, 32, 32);
        this.setOpaque(false);
        this.setBackground(TRANSPARENT);
        
        this.addMouseListener(pListener);
        setBlock(this.aMaterial);
    }
    
    public Location getLocations(){ return this.aLocation; }
    
    public Material getType(){ return this.aMaterial; }
    
    public GameEngine getEngine(){ return this.aEngine; }
    
    public void destroy(){
        if(this.getType().getData().isBreakable()){
            if(this.getType().getData().isLootable())
                this.aEngine.getWorld().spawn(new Item(this.getType(), 1, this.aEngine, this.getLocations()));
            else{
                if(this.getType().getData().hasLootingTable())
                    for(Material mat : this.getType().getData().getLoots()){
                        this.aEngine.getWorld().spawn(new Item(mat, 1, this.aEngine, this.getLocations()));
                    }
            }
            this.setBlock(Material.AIR);
        }
    }
    
    public void setBlock(Material mat){
        this.aMaterial = mat;
        this.tick = 0;
        if(!mat.equals(Material.AIR)){
            this.setOpaque(true);
            this.setContentAreaFilled(true);
            this.update();
        } else {
            this.setOpaque(false);
            this.setContentAreaFilled(false);
        }
        this.update();
    }
    
    public void setBlock(Material mat, int tick){
        this.setBlock(mat);
        this.tick = tick;
    }
    
    public void tick(){
        if(this.aMaterial.getData() instanceof TickingBlock data && this.aEngine.getWorld() != null){
            data.tick(this);
        }
    }
    
    public boolean hasBlockInRange(Material mat, int range){
        int xStart = (int)this.getLocations().getX() - range;
        int xEnd = (int)this.getLocations().getX() + range;
        int yStart = (int)this.getLocations().getY() - range;
        int yEnd = (int)this.getLocations().getY() + range;
        
        for(int x = xStart; x<= xEnd; x++){
            for(int y = yStart; y<= yEnd; y++){
                if(this.aEngine.getWorld() == null) return true;
                Block bloc = this.aEngine.getWorld().getBlockAt(x, y);
                if(bloc == null) continue;
                if(bloc.getType() == mat) return true;
            }
        }
        return false;
    }
    
    public boolean isTick(int tick){
        return this.tick == tick;
    }
    
    public void setTick(int tick){
        this.tick = tick;
    }
    
    public void addTick(){ this.tick++; }
    
    public boolean hasCollision(){
        return this.aMaterial.getData().hasCollisions();
    }
    
    public void update(){
        this.setIcon( this.aMaterial.getTexture() );
    }
}
