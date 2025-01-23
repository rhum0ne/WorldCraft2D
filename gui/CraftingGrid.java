package gui;

import engine.GameEngine;
import engine.Craft;
import entities.Item;
/**
 * Décrivez votre classe CraftingGrid ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CraftingGrid
{
    private GameEngine aEngine;
    
    private CraftingSlot resultSlot;
    private CraftingSlot[] slots;
    
    private ItemInfoPanel aIPanel;
    private InventoryPanel aPanel;
    
    public CraftingGrid(int size, int x, int y, final InventoryPanel pPanel, final ItemInfoPanel iPanel, final GameEngine pEngine){
        this.aEngine = pEngine;
        this.aIPanel = iPanel;
        this.aPanel = pPanel;
        this.resultSlot = new CraftingSlot(this, this.aPanel, iPanel, 10, x+148, y+38);
        this.aPanel.add(this.resultSlot);
        this.slots = new CraftingSlot[size];
            int a = (int)Math.sqrt(size);
        for(int i=0; i<slots.length; i++){
            slots[i] = new CraftingSlot(this, this.aPanel, iPanel, i, 2+x+InventorySlot.SLOT_SPACE*(i%a), 2+y+InventorySlot.SLOT_SPACE*(i/a));
            this.aPanel.add(slots[i]);
        }
    }
    
    public CraftingSlot getResultSlot(){ return this.resultSlot; }
    
    public void setSlot(int index, CraftingSlot slot){
        this.slots[index] = slot;
    }
    
    public void updateResult(){
        for(Craft craft : this.aEngine.getCrafts()){
            if(isValid(craft)){
                this.resultSlot.setItem(craft.getResult());
                return;
            }
        }
        this.resultSlot.setItem(null);
    }
    
    private boolean isValid(Craft craft){
        for(int i=0; i<slots.length; i++){
            if(slots[i].getItem() == null){
                if(craft.getItems()[i] != null) return false;
                else continue;
            }
            if(!slots[i].getItem().getType().equals(craft.getItems()[i])) return false;
        }
        return true;
    }
    
    public void craft(){
        if(this.aPanel.hasSelectedItem()){
            if(!this.aPanel.getSelectedItem().isSameAs(this.resultSlot.getItem())) return;
            
            this.aPanel.getSelectedItem().addQuantity(this.resultSlot.getItem().getQuantity());
        } else this.aPanel.setSelectedItem(Item.clone(this.resultSlot.getItem()));
        
        for(int i=0; i<slots.length; i++){
            Item item = slots[i].getItem();
            if(item != null){
                item.removeQuantity(1);
                if(item.getQuantity() == 0) slots[i].setItem(null);
            }
        }
        
    }
}
