package commands;

import engine.GameEngine;
import entities.Player;
/**
 * Décrivez votre classe GodCommand ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GodCommand extends Command
{
    public GodCommand(GameEngine pEngine){
        super(pEngine);
    }
    
    public void execute(Player sender, String label, String[] args){
        if(args.length == 0){
            sender.changeGodMode();
        }
        else {
            boolean a = Boolean.parseBoolean(args[0]);
            sender.setGodMode(a);
        }
        
        if(sender.isInGodMode()){
            sender.sendMessage("You are now in god mode.");
        } else sender.sendMessage("You are no longer in god mode.");
    }
}
