package entities;

import javax.swing.JButton;
import java.util.UUID;
import java.awt.Color;

import engine.GameEngine;
import engine.Location;
import engine.Block;
import engine.Material;
/**
 * Décrivez votre classe Entity ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Entity extends JButton implements Runnable
{
    public static Color TRANSPARENT = new Color(0, 0, 0, 0);
    
    private GameEngine aEngine;
    private Location aLoc;
    private UUID aID;
    
    //-1 = left ; 0 = static ; 1 = right
    private int xDirection;
    
    private double aXSpeed;
    private double aYSpeed;
    
    private String name;
    
    /**
     * Constructeur d'objets de classe Entity
     */
    public Entity(final String name, final GameEngine pEngine, final Location pLoc, int sizeX, int sizeY)
    {
        this.name = name;
        this.aEngine = pEngine;
        this.aLoc = new Location(pLoc);
        this.aID = UUID.randomUUID();
        
        if(name == null) 
            this.name = this.aID.toString();
        
        this.aEngine.getEntitiesManager().add(this);
        this.setBackground(new Color(200, 200, 0));
        
        this.addMouseListener(pEngine.getGUI().getWorldEventsListener());
        this.setBounds((int)pLoc.getXPixel(), (int)pLoc.getYPixel(), sizeX, sizeY);
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setY(double pY){
        this.aLoc.setY(pY);
        this.setBounds((int)(aLoc.getX()*32), (int)(pY)*32, this.getWidth(), this.getHeight());
    }

    public int getXDirection(){ return this.xDirection; }
    public void setXDirection(final int dir){ this.xDirection = dir; }
    
    public void addYSpeed(double a){ this.aYSpeed += a; }
    public void addXSpeed(double a){ this.aXSpeed += a; }
    public void setYSpeed(double a){ this.aYSpeed = a; }
    public void setXSpeed(double a){ this.aXSpeed = a; }
    
    public void run(){
        if(!this.aEngine.isInGame()) return;
        this.applyGravity();
        this.move();
        this.update();
    }
    
    private void applyGravity(){
        if(this.hasBlockBelow()){
            if (this.aYSpeed > 0.0){
                if(this instanceof LivingEntity e && this.aYSpeed > 0.35){
                    e.makeDamages((int)(this.aYSpeed*6));
                }
                this.aYSpeed = 0.0;
                return;
            } else if (Double.compare(this.aYSpeed, 0.0) == 0) return;
        }
        
        this.aYSpeed += 0.018;
        if(this.aYSpeed > 0)
            for(double i = 0.0; i<=this.aYSpeed; i+=0.001){
                if(this.hasBlockBelow()) break;
                this.getLocations().addY(0.001);
            }
            
        else
            for(double i = 0.0; i>this.aYSpeed; i-=0.001){
                if(this.hasBlockTop()) {
                    this.aYSpeed = 0;
                    break;
                }
                this.getLocations().addY(-0.001);
            }
    }
    
    public void move(){
        double speed = 0;
        
        speed += this.aXSpeed;
        if(this instanceof LivingEntity e) { 
            speed += this.xDirection * e.getSpeed()/100.0;
        }
        
        if(speed != 0){ 
            if(Math.abs(speed) < 0.002) {
                this.aXSpeed = 0;
                
            }
            else if(speed > 0)
                for(double i = 0.0; i<=speed; i+=0.001){
                    if(this.hasBlockRight()) {
                        speed = 0;
                        break;
                    }
                    this.getLocations().addX(0.001);
                }
            
            else
                for(double i = 0.0; i>speed; i-=0.001){
                    if(this.hasBlockLeft()) {
                        speed = 0;
                        break;
                    }
                    this.getLocations().addX(-0.001);
            }
            
            if(this instanceof Player p) p.getSaturation().remove(Math.min((int)(Math.abs(4*this.aXSpeed)), 50)); 
            this.aXSpeed *= this.getBlockTypeBelow().getData().getRoughness();
        }
    }
    
    public double getYSpeed(){ return this.aYSpeed; }
    
    public Material getBlockTypeBelow(){
        return this.aEngine.getWorld().getBlockAt(((int)((this.getLocations().getXPixel()+this.getWidth()/2)/32.0)), (int)(this.getLocations().getY()+((this.getHeight()+1)/32.0))).getType();
    }
    
    public boolean hasBlockBelow(){
        Block block = this.aEngine.getWorld().getBlockAt(((int)((this.getLocations().getXPixel()+6)/32)), (int)(this.getLocations().getY()+((this.getHeight()+1)/32.0)));
        Block block2 = this.aEngine.getWorld().getBlockAt(((int)((this.getLocations().getXPixel()+26)/32)), (int)(this.getLocations().getY()+((this.getHeight()+1)/32.0)));
        
        //return !(block == null || block.getType().equals(Material.AIR) || block2 == null || block2.getType().equals(Material.AIR));
        if(!(block == null || !block.hasCollision())) return true;
        if (!(block2 == null || !block2.hasCollision())) return true;
        return false;
    }
    
    public boolean hasBlockTop(){
        Block block = this.aEngine.getWorld().getBlockAt(((int)((this.getLocations().getXPixel()+6)/32)), (int)(this.getLocations().getY()));
        Block block2 = this.aEngine.getWorld().getBlockAt(((int)((this.getLocations().getXPixel()+26)/32)), (int)(this.getLocations().getY()));
        
        //return !(block == null || block.getType().equals(Material.AIR) || block2 == null || block2.getType().equals(Material.AIR));
        if(!(block == null || !block.hasCollision())) return true;
        if (!(block2 == null || !block2.hasCollision())) return true;
        return false;
    }
    
    public boolean hasBlockRight(){
        for(int i=0; i<=(int)(this.getHeight()/32); i++){
            Block block;
            if(i==0) block = this.aEngine.getWorld().getBlockAt((int)this.getLocations().getX()+this.getWidth()/32, (int)(this.getLocations().getY()+1.0/32.0));
            else block = this.aEngine.getWorld().getBlockAt((int)this.getLocations().getX()+this.getWidth()/32, (int)(this.getLocations().getY())+i);
            if(!(block == null || !block.hasCollision())) return true;
        }
        
        return false;
    }
    
    public boolean hasBlockLeft(){
        for(int i=0; i<=(int)(this.getHeight()/32); i++){
            
            Block block;
            if(i==0) block = this.aEngine.getWorld().getBlockAt((int)this.getLocations().getX(), (int)(this.getLocations().getY()+1.0/32.0));
            else block = this.aEngine.getWorld().getBlockAt((int)this.getLocations().getX(), (int)(this.getLocations().getY())+i);
            
            if(!(block == null || !block.hasCollision())) return true;
            
            //block = this.aEngine.getWorld().getBlockAt((int)this.getLocations().getX(), (int)(this.getLocations().getY()+30/32)+i);
            //if(!(block == null || !block.hasCollision())) return true;
        }
        
        return false;
    }
    
    public void setCoordinates(int pX, int pY){
        this.setBounds(pX, pY, this.getWidth(), this.getHeight());
    }
    
    private void update(){
        this.setBounds(this.aLoc.getXPixel(), this.aLoc.getYPixel(), this.getWidth(), this.getHeight());
    }
    
    public void delete(){
        this.aEngine.delete(this);
    }
    
    public Location getLocations(){ return this.aLoc; }
    public UUID getID(){ return this.aID; }
    public GameEngine getEngine(){ return this.aEngine; }
}
