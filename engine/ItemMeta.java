package engine;

import engine.Block;
import entities.Item;
/**
 * Décrivez votre classe ItemMeta ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ItemMeta
{
    private Item aItem;
    
    public ItemMeta(Item item){
        this.aItem = item;
    }
    
    public void onClick(Block block){
        block.setBlock(this.aItem.getType());
    }
    
    @Override
    public boolean equals(final Object pO){
        if(!(pO instanceof ItemMeta)) return false;
        ItemMeta pMeta = (ItemMeta)pO;
        return true;
    }
}
