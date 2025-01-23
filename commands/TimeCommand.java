package commands;

import engine.GameEngine;
import entities.Player;
/**
 * Décrivez votre classe TimeCommand ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class TimeCommand extends Command
{
    public TimeCommand(GameEngine pEngine){
        super(pEngine);
    }
    
    public void execute(Player sender, String label, String[] args){
        sender.sendMessage(this.getEngine().getWorld().getTime() + " ticks");
    }
}
