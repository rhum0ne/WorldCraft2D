package entities;

import engine.GameEngine;
import engine.Location;
import engine.Material;
import java.util.List;
import java.util.ArrayList;
import engine.Block;
import engine.InteractableMaterial;
import engine.MeleeWeapon;

/**
 * Décrivez votre classe LivingEntity ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class LivingEntity extends Entity
{
    private Inventory aInventory;
    private EntityConstant aLife;
    private int aReach;
    
    private int damages;
    private double aSpeed;
    
    private List<Item> lootingTable;
    
    public LivingEntity(final String name, final GameEngine pEngine, final Location pLoc, int speed, int pReach, int x, int y){
        super(name, pEngine, pLoc, x, y);
        
        this.aInventory = (this instanceof Player) ? new PlayerInventory((Player)this) : new Inventory(this, 24);
        
        this.damages = 5;
        this.aSpeed = speed;
        
        this.aLife = new EntityConstant("Life", 20, 0, 20, this, true);
        this.lootingTable = new ArrayList<Item>();
        this.aReach = pReach;
    }
    
    public EntityConstant getLife(){ return this.aLife; }
    
    public void makeDamages(final int a){
        this.aLife.remove(a);
    }
    
    public boolean canReach(Entity e){
        return this.getLocations().getDistanceFrom(e.getLocations()) <= this.aReach;
    }
    
    public boolean canReach(Block bloc){
        return this.getLocations().getDistanceFrom(bloc.getLocations()) <= this.aReach;
    }
    
    public void interact(Block bloc){
        if(!this.canReach(bloc)) return;
        
        InteractableMaterial data = (InteractableMaterial)bloc.getType().getData();
        data.onInteract(this, bloc);
    }
    
    public void hit(LivingEntity e){
        if(!this.canReach(e)) return;
        if(e instanceof PassiveEntity pE) pE.setPanic(true);
        int damage = this.damages;
        if(this instanceof Player p && ((PlayerInventory)p.getInventory()).getItemInHand().getType().getData() instanceof MeleeWeapon w) damage += w.getDamages();
        e.makeDamages(damage);
        int direction = 0;
        if(this.getLocations().getX() > e.getLocations().getX()) direction = -1;
        else if(this.getLocations().getX() < e.getLocations().getX()) direction = 1;
        e.addXSpeed(direction*this.damages/30.0);
        e.addYSpeed(-0.15);
    }
    
    public void heal(){
        this.aLife.set(this.aLife.getMax());
    }
    
    public double getSpeed(){ return this.aSpeed; }
    public void setSpeed(final double a){ this.aSpeed = a; }
    
    @Override
    public void run(){
        if(!(this instanceof Player p)){ 
            if(Math.random() < 0.02) this.changeDirection();
        
            if(this.hasBlockInFront()) this.jump();
        } else this.getEngine().getGUI().updateLocation(p);
        
        super.run();
    }
    
    public boolean hasBlockInFront(){
        if(this.getXDirection() == -1) return this.hasBlockLeft();
        if(this.getXDirection() == 1) return this.hasBlockRight();
        return false;
    }
    
    public void changeDirection(){
        double rdm = Math.random();
        if(rdm < 0.3) this.setXDirection(-1);
        else if(rdm < 0.6) this.setXDirection(0);
        else this.setXDirection(1);
    }
    
    public void heal(final int a){
        this.aLife.add(a);
    }
    
    public void addLoot(Material mat, int quantity){
        this.lootingTable.add(new Item(mat, quantity, this.getEngine(), new Location()));
    }
    
    public void kill(){
        this.setVisible(false);
        for(Item item : this.lootingTable){
            Item loot = Item.clone(item);
            loot.getLocations().set(this.getLocations());
            this.getEngine().getWorld().spawn(loot);
        }
        this.delete();
    }
    
    public boolean jump(){
        if(this.hasBlockBelow()){
            this.setYSpeed(-0.22);
            return true;
        }
        return false;
    }
    
    public void give(final Item item){
        this.getInventory().addItem(item);
        this.getEngine().getGUI().updateInventory();
    }
    
    public void take(final Item item){
        this.getEngine().getWorld().remove(item);
        this.getInventory().addItem(item);
        this.getEngine().getGUI().updateInventory();
    }
    
    public void drop(final Item item){
        if(this.getInventory().contains(item)){
            this.getInventory().removeItem(item);
        }
        item.getLocations().set(this.getLocations());
        this.getEngine().getWorld().spawn(item);
        this.getEngine().getGUI().updateInventory();
    }
    
    public int getReach(){ return this.aReach; }
    
    public Inventory getInventory(){ return this.aInventory; }
    
    public boolean isAlive(){ return this.aLife.get() > this.aLife.getMin(); }
    public boolean hasLoots(){ return this.lootingTable.size() != 0; }
}
