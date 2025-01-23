package key;

import entities.Player;
/**
 * Décrivez votre classe SprintKey ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class EscapeKey extends Key
{
    public void onKeyPressed(Player player){
        try
        {
            player.getEngine().getWorld().save();
            player.getEngine().getGUI().setPlaying(false);
            player.getEngine().reset();
            player.getEngine().getGUI().getMenuPanel().repaint();
            player.getEngine().getGUI().getMenuPanel().revalidate();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    public void onKeyReleased(Player player){
        
    }
}
