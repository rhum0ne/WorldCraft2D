package commands;

import engine.GameEngine;
import entities.Player;
/**
 * Décrivez votre classe Command ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class Command
{
    private GameEngine aEngine;
    
    public Command(GameEngine pEngine){
        this.aEngine = pEngine;
    }
    
    public GameEngine getEngine(){ return this.aEngine; }
    
    public abstract void execute(Player sender, String label, String[] args);
}
