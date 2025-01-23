package key;

import entities.Player;
/**
 * Décrivez votre classe InventoryKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class InventoryKey extends Key
{
    public void onKeyPressed(Player player){
        player.getEngine().getGUI().showInventory();
    }
    
    public void onKeyReleased(Player player){
        
    }
}
