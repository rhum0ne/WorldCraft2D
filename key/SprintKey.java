package key;

import entities.Player;
/**
 * Décrivez votre classe SprintKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SprintKey extends Key
{
    public void onKeyPressed(Player player){
        player.setSpeed(Player.RUN_SPEED);
    }
    
    public void onKeyReleased(Player player){
        player.setSpeed(Player.WALK_SPEED);
    }
}
