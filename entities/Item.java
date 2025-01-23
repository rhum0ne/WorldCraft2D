package entities;

import javax.swing.ImageIcon;
import engine.Material;
import engine.GameEngine;
import engine.ItemMeta;
import engine.Location;
import java.awt.Color;

/**
 * Décrivez votre classe Item ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Item extends Entity
{
    private Material aMat;
    private ItemMeta aMeta;
    private int quantity;
    
    public Item(Material pMat, int quantity, GameEngine pEngine, Location pLoc){
        super(pMat.getName(), pEngine, pLoc, 32, 32);
        this.aMat = pMat;
        this.quantity = quantity;
        this.aMeta = new ItemMeta(this);
        
        this.setBackground(Entity.TRANSPARENT);
        this.setIcon( this.aMat.getTexture() );
    }
    
    public boolean isStackFull(){
        return false;
    }
    
    public void setQuantity(final int pQ) { this.quantity = pQ;}
    
    public void addQuantity(final int pQ){ this.quantity += pQ; }
    public void removeQuantity(final int pQ){ this.quantity -= pQ; }
    
    public int getQuantity(){ return this.quantity; }
    
    public ItemMeta getItemMeta(){ return this.aMeta; }
    
    public Material getType(){ return this.aMat; }
    
    public ImageIcon getTexture(){ return this.aMat.getTexture(); }
    
    public boolean isSameAs(final Item pItem){
        return pItem.aMat == this.aMat && this.aMeta.equals(pItem.aMeta);
    }
    
    public String toString(){
        return quantity + "x - " + aMat;
    }
    
    public static Item clone(Item item){
        return new Item(item.getType(), item.getQuantity(), item.getEngine(), new Location(item.getLocations()));
    }
}
