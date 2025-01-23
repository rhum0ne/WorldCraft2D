package key;

import entities.Player;
/**
 * Décrivez votre classe JumpKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class JumpKey extends Key
{
    public JumpKey(){ super(true); }
    
    public void onKeyPressed(Player player){
        player.jump();
    }
    
    public void onKeyReleased(Player player){
        
    }
}
