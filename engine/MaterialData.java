package engine;

import entities.Item;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Décrivez votre classe MaterialData ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class MaterialData
{
    private boolean breakable;
    private boolean placeable;
    private boolean lootable;
    private boolean hasCollisions;
    private double roughness;
    private List<Material> lootingTable;
    
    public MaterialData(final boolean breakable, final boolean placeable, final boolean lootable, final boolean collisions, final double roughness){
        this.breakable = breakable;
        this.placeable = placeable;
        this.lootable = lootable;
        this.hasCollisions = collisions;
        this.lootingTable = new ArrayList<>();
        this.roughness = roughness;
    }
    
    public MaterialData(final boolean breakable, final boolean placeable, final boolean lootable, final boolean collisions){
        this(breakable, placeable, lootable, collisions, 0.75);
    }
    
    public MaterialData(final boolean breakable, final boolean placeable, final boolean lootable){
        this(breakable, placeable, lootable, true, 0.75);
    }
    
    public List<Material> getLoots(){
        return this.lootingTable;
    }
    
    public void addLoots(Material[] materials){
        this.lootable = false;
        Collections.addAll(this.lootingTable, materials);
    }
    
    public void addLoot(Material material){
        this.lootable = false;
        this.lootingTable.add(material);
    }
    
    public double getRoughness(){ return this.roughness; }
    
    public boolean hasLootingTable(){
        return this.lootingTable.size() !=0;
    }
    public boolean isLootable(){ return this.lootable; }
    public boolean isBreakable(){ return this.breakable; }
    public boolean isPlaceable(){ return this.placeable; }
    public boolean hasCollisions(){ return this.hasCollisions; }
}
