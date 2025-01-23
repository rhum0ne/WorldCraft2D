package entities;

import engine.GameEngine;
import engine.Location;
import engine.Material;
/**
 * Décrivez votre classe Pig ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pig extends PassiveEntity
{
    public Pig(final GameEngine pEngine, final Location pLoc){
        super(pEngine, pLoc, 3, 0, 51, 32);
        this.addLoot(Material.BACON, 2);
        
        this.setBackground(TRANSPARENT);
        this.setIcon( Entities.PIG.getTexture() );
    }
}
