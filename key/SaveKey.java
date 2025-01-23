package key;

import entities.Player;
/**
 * Décrivez votre classe SprintKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SaveKey extends Key
{
    public void onKeyPressed(Player player){
        try
        {
            player.getEngine().getWorld().save();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    public void onKeyReleased(Player player){
        
    }
}
