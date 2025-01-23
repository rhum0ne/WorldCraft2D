package gui;

/**
 * Décrivez votre classe CraftingSlot ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CraftingSlot extends InventorySlot
{
    private CraftingGrid grid;
    
    public CraftingSlot(final CraftingGrid grid,final InventoryPanel pPanel, final ItemInfoPanel iPanel, final int index, final int pX, final int pY)
    {
        super(pPanel, iPanel, index, pX, pY);
        this.grid = grid;
    }

    
    public CraftingGrid getCraftingGrid(){ return this.grid; }
}
