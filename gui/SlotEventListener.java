package gui;

import engine.GameEngine;
import entities.Item;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Décrivez votre classe SlotEventListener ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SlotEventListener extends MouseAdapter
{
    private final ItemInfoPanel aIPanel;
    private final InventorySlot aSlot;
    private final InventoryPanel aPanel;
    
    /**
     * Constructs a MouseEventListener object.
     * 
     * @param pSlot The inventory slot associated with the listener.
     * @param pIPanel The item info panel to display item information.
     */
    public SlotEventListener(final InventorySlot pSlot ,final ItemInfoPanel pIPanel, final InventoryPanel pPanel){
        this.aIPanel = pIPanel;
        this.aSlot = pSlot;
        this.aPanel = pPanel;
    }
    
    /**
     * Handles mouse entering the inventory slot.
     * 
     * @param pMe The mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent pMe){
        aSlot.setOpaque(true);
        if(aSlot.hasItem()){
            Item vItem = aSlot.getItem();
            this.aIPanel.setItem(vItem);
            this.aIPanel.setPos(aSlot.getPanel().getX() + aSlot.getX() + 33, aSlot.getPanel().getY() + aSlot.getY() - 16);
            this.aIPanel.setVisible(true);
        }
    }
    
    /**
     * Handles mouse exiting the inventory slot.
     * 
     * @param pMe The mouse event.
     */
    @Override
    public void mouseExited(MouseEvent pMe){
        aSlot.setOpaque(false);
        this.aIPanel.setVisible(false);
    }
    
    /**
     * Handles mouse pressing in the inventory slot.
     * 
     * @param pMe The mouse event.
     */
    @Override
    public void mousePressed(MouseEvent pMe) {
        GameEngine vEngine = this.aSlot.getPanel().getEngine();
        Item vItem = this.aSlot.getItem();
        
        if(this.aSlot instanceof CraftingSlot){
            this.updateCraftingGrid(pMe, vEngine, vItem);
            return;
        }
        
        if (pMe.getButton() == MouseEvent.BUTTON1){
            this.aSlot.setItem(this.aPanel.getSelectedItem());
            vEngine.getPlayer().getInventory().setItem(this.aSlot.getIndex(), this.aSlot.getItem());
            this.aPanel.setSelectedItem(vItem);
        } else if (pMe.getButton() == MouseEvent.BUTTON3){
            if(!this.aSlot.hasItem()) return;
            vEngine.getPlayer().drop(vItem);
            this.aIPanel.setVisible(false);
        } 
    }
    
    private void updateCraftingGrid(MouseEvent pMe, GameEngine pEngine, Item pItem){
        CraftingSlot slot = (CraftingSlot) this.aSlot;
        
        if(slot.equals(slot.getCraftingGrid().getResultSlot())){
            if(slot.hasItem()){
                slot.getCraftingGrid().craft();
                slot.getCraftingGrid().updateResult();
            }
            return;
        }
        
        if (pMe.getButton() == MouseEvent.BUTTON1){
            this.aSlot.setItem(this.aPanel.getSelectedItem());
            this.aPanel.setSelectedItem(pItem);
            slot.getCraftingGrid().updateResult();
        } else if (pMe.getButton() == MouseEvent.BUTTON3){
            Item vItem = Item.clone(this.aPanel.getSelectedItem());
            vItem.setQuantity(1);
            this.aSlot.setItem(vItem);
            this.aPanel.getSelectedItem().removeQuantity(1);
            if(this.aPanel.getSelectedItem().getQuantity() == 0) this.aPanel.setSelectedItem(null);
            slot.getCraftingGrid().updateResult();
        } 
    }
}
