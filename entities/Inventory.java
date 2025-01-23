package entities;

import java.util.HashMap;
import java.util.List;
import java.util.*;
/**
 * ItemList class - represents a collection of items.
 *
 * This class manages a list of items, keeping track of their quantities and providing operations
 * to add, remove, and check items in the list.
 * 
 * @author Romain ELETUFE
 * @version 2.0
 */
public class Inventory
{
    private final Item[] items;
    private final Entity aHolder;
    
    /**
     * Constructor for the ItemList class.
     *
     * @param pHolder The object holding the item list.
     */
    public Inventory(Entity pHolder, int size){
        this.items = new Item[size];
        this.aHolder = pHolder;
    }
    
    public List<Item> getItems(){
        return Arrays.asList(items);
    }
    
    public int size(){
        return items.length;
    }
    
    public Item getItem(int slot){
        if(slot < size())
            return this.items[slot];
        return null;
    }
    
    /*public int[] getEquivalentsSlots(final Item pItem){
        int[] slots = new int[items.length];
        int i=0;
        for(int slot = 0; slot<items.length; slot++){
            if(items[slot] == null) continue;
            if(items[slot].equals(pItem)){
                slots[i] = slot;
                i++;
            }
        }
        return slots;
    }*/
    
    /**
     * Add an item to the list.
     *
     * @param pItem The item to add to the list.
     */
    public void addItem(final Item pItem){
        for(int slot = 0; slot<items.length; slot++){
            if(items[slot] == null) continue;
            if(items[slot].isSameAs(pItem) && !items[slot].isStackFull()){
                items[slot].addQuantity(pItem.getQuantity());
                return;
            }
        }
        //Sinon, si on a pas déjà l'item dans l'inventaire alors on le met dans un nouveau slot.
        int slot = this.getEmptySlot();
        if(slot==-1){
            System.out.println("no empty slot.");
            return;
        }
        this.setItem(slot, pItem);
    }
    
    public int getEmptySlot(){
        for(int i=0; i<items.length; i++)
            if(items[i] == null) return i;
        return -1;
    }
    
    public void setItem(int index, Item item){
        this.items[index] = item;
        if(this.aHolder instanceof Player){
            Player vPlayer = (Player) this.aHolder;
            vPlayer.updateInventory();
        }
    }
    
    /**
     * Remove an item from the list.
     *
     * @param pItem The item to remove from the list.
     */
    public void removeItem(final Item pItem){
        int slot = -1;
        for(int i=0; i<items.length; i++){
            if(items[i] == null) continue;
            if(items[i].isSameAs(pItem)) slot = i;
        }
        if(slot==-1){
            System.out.println("ERROR: no item to remove for: " + this.aHolder.toString() + " ; " + pItem.getName());
            return;
        }
        else {
            if(pItem.getQuantity()<2) this.setItem(slot, null);
            else this.items[slot].removeQuantity(1);
        }
    }
    
    /**
     * Remove an item from the list.
     *
     * @param pItem The item to remove from the list.
     */
    public void removeItem(final Item pItem, int quantity){
        if(!this.contains(pItem, quantity)) {
            System.out.println("Entity doesn't have " + quantity + " " + pItem.getType());
        }
        while(quantity > 0){
            if(!this.contains(pItem)) break;
            removeItem(pItem);
            quantity--;
        }
    }
    
    public Item getFirstItem(){
        for(int i=0; i<this.items.length; i++){
            if(items[i] != null) return items[i];
        }
        return null;
    }
    
    /**
     * Check if the item list is empty.
     *
     * @return true if the item list is empty, false otherwise.
     */
    public boolean isEmpty(){
        for(int i=0; i<this.items.length; i++){
            if(items[i] != null) return false;
        }
        return true;
    }
    
    /**
     * Check if the list contains a specific item.
     *
     * @param pItem The item to check for.
     * @return true if the list contains the item, false otherwise.
     */
    public boolean contains(final Item pItem){
        for(int i=0; i<this.items.length; i++){
            if(items[i] == null) continue;
            if(items[i].isSameAs(pItem)) return true;
        }
        return false;
    }
    
    public boolean contains(final Item pItem, final int quantity){
        int a = 0;
        for(int i=0; i<this.items.length; i++){
            if(items[i] == null) continue;
            if(items[i].isSameAs(pItem)) a+= items[i].getQuantity();
        }
        return a >= quantity;
    }
    
    /**
     * Get a string representation of the item list.
     *+
     * @return A string representation of the item list.
     */
    @Override
    public String toString(){
        if(this.isEmpty()) return "No items in ItemList";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<items.length; i++){
            Item item = items[i];
            if(item == null) continue;
            sb.append("- ").append(item.getQuantity()).append("x : ").append(item.getName()).append("\n");
        }
        return sb.toString();
    }
}
