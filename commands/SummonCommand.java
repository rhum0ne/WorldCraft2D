package commands;

import engine.GameEngine;
import engine.Material;
import entities.Player;
import entities.Pig;
import entities.Item;
/**
 * Décrivez votre classe SummonCommand ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SummonCommand extends Command
{
    public SummonCommand(GameEngine pEngine){
        super(pEngine);
    }
    
    public void execute(Player sender, String label, String[] args){
            if(args.length == 0){
                sender.sendMessage("You need to specity which entity you want.");
                return;
            }
        
            Material material = Material.parseMaterial(args[0]);
            if(material == null){
                if(spawnMob(sender, args[0])) return;
                sender.sendMessage("Invalid material");
                return;
            }
            this.getEngine().getWorld().spawn(new Item(material, 1, this.getEngine(), sender.getLocations()));
            sender.sendMessage("Summoned " + material.getName() + ".");
    }
    
    private boolean spawnMob(Player p, String s){
        if(s.equalsIgnoreCase("pig")){
            this.getEngine().getWorld().spawn(new Pig(this.getEngine(), p.getLocations()));
            p.sendMessage("Summoned " + s + ".");
            return true;
        }
        return false;
    }
}
