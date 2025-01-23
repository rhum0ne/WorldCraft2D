package engine;

import entities.Item;
/**
 * Décrivez votre classe Craft ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Craft
{
    private Material[] items;
    private Item result;
    
    public Craft(Material[] items, Item result){
        this.items = items;
        this.result = result;
    }
    
    public Material[] getItems(){ return this.items; }
    public Item getResult(){ return this.result; }
}
