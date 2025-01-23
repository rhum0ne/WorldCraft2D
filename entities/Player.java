package entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Timer;


import engine.GameEngine;
import engine.Location;
import engine.Sounds;
/**
 * Décrivez votre classe Player ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Player extends LivingEntity
{
    public static int WALK_SPEED = 5;
    public static int RUN_SPEED = 10;
    
    private EntityConstant aHunger;
    private EntityConstant aSaturation;
    private boolean godMode = false;
    
    /**
     * Constructeur d'objets de classe Player
     */
    public Player(final GameEngine pEngine)
    {
        super("RhumOne", pEngine, new Location(5.5, 2.5), WALK_SPEED, 4, 32, 64);
        this.aHunger = new EntityConstant("Hunger", 20, 0, 20, this, false);
        this.aSaturation = new EntityConstant("Saturation", 1000, 0, 1000, this, false);
        PlayerTask task = new PlayerTask(this);
        Timer timer = new Timer();
        timer.schedule(task, 0, 500);
        
        this.setBackground(TRANSPARENT);
        this.setIcon( Entities.PLAYER.getTexture() );
    }
    
    public void playSound(final Sounds pSound){
        if(pSound == null) return;
        this.getEngine().getGUI().playSound(pSound);
    }
    
    public boolean hasItemInHand(){
        return ((PlayerInventory) this.getInventory()).getItemInHand() != null;
    }
    
    public Item getItemInHand(){
        return ((PlayerInventory) this.getInventory()).getItemInHand();
    }
    
    public EntityConstant getHunger(){ return this.aHunger; }
    public EntityConstant getSaturation(){ return this.aSaturation; }
    
    public void makeDamages(int damages){
        if(!godMode) { 
            super.makeDamages(damages);
            this.playSound(Sounds.HURT);
        }
    }
    
    public boolean isInGodMode(){
        return this.godMode;
    }
    
    public void changeGodMode(){ this.godMode = !godMode; }
    public void setGodMode(final boolean a){ this.godMode = a; }
    
    public boolean jump(){
        boolean result = super.jump();
        if(result) this.aSaturation.remove(8);
        return result;
    }
    
    public void sendMessage(final String msg){
        this.getEngine().getGUI().showMessage(msg);
    }
    
    @Override
    public void kill(){
        this.respawn();
    }
    
    public void respawn(){
        this.getLocations().setCoordinates(16, 5);
        this.getLife().setMax();
    }
    
    public void updateInventory(){ 
        this.getEngine().getGUI().updateInventory();
    }
    
}
