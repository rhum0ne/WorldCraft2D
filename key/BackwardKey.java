package key;

import entities.Player;
/**
 * Décrivez votre classe BackwardKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BackwardKey extends Key
{
    public void onKeyPressed(Player player){
        player.setXDirection(player.getXDirection()-1);
    }
    
    public void onKeyReleased(Player player){
        player.setXSpeed(player.getXDirection() * player.getSpeed()/100.0);
        player.setXDirection(player.getXDirection()+1);
    }
}
