package key;

import entities.Player;
/**
 * Décrivez votre classe Key ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class Key
{
    private boolean isRepeatable;
    
    public Key(boolean repeat){
        this.isRepeatable = repeat;
    }
    
    public Key(){ this(false); }
    
    public boolean isRepeatable(){ return this.isRepeatable; }
    
    public abstract void onKeyPressed(Player player);
    public abstract void onKeyReleased(Player player);
}
