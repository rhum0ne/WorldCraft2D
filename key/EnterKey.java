package key;

import entities.Player;
/**
 * Décrivez votre classe EnterKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class EnterKey extends Key
{
    public void onKeyPressed(Player player){
        player.getEngine().getGUI().setShowingChat(!player.getEngine().getGUI().isChatVisible());
    }
    
    public void onKeyReleased(Player player){
        
    }
}
