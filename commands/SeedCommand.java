package commands;

import engine.GameEngine;
import entities.Player;
/**
 * Décrivez votre classe GodCommand ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SeedCommand extends Command
{
    public SeedCommand(GameEngine pEngine){
        super(pEngine);
    }
    
    public void execute(Player sender, String label, String[] args){
        sender.sendMessage("This world's Seed is: " + this.getEngine().getWorld().getSeed().toString());
    }
}
