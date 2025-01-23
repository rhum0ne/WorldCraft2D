package entities;


/**
 * Décrivez votre classe PlayerInventory ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PlayerInventory extends Inventory
{
    private static int HANDSLOTS = 6;
    
    private int aHandingSlot;
    
    public PlayerInventory(Player player){
        super(player, 36);
        this.aHandingSlot = 0;
    }
    
    public Item getItemInHand(){ return this.getItem(this.aHandingSlot); }
    
    public void setHandingSlot(final int a){
        this.aHandingSlot = a%HANDSLOTS;
    }
    
    public void addHandingSlot(){
        this.aHandingSlot = this.aHandingSlot++%HANDSLOTS;
    }
        
    public void remHandingSlot(){
        this.aHandingSlot = this.aHandingSlot--%HANDSLOTS;
    }
}
