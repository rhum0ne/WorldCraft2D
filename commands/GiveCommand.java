package commands;

import engine.GameEngine;
import engine.Material;
import engine.Location;
import entities.Player;
import entities.Pig;
import entities.Item;
/**
 * Décrivez votre classe SummonCommand ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GiveCommand extends Command
{
    public GiveCommand(GameEngine pEngine){
        super(pEngine);
    }
    
    public void execute(Player sender, String label, String[] args){
            if(args.length == 0){
                sender.sendMessage("You need to specity which entity you want.");
                return;
            }
        
            Material material = Material.parseMaterial(args[0]);
            if(args[0].equalsIgnoreCase("all")){
                giveAll(sender, args);
                return;
            }
            
            if(material == null){
                sender.sendMessage("Invalid material");
                return;
            }
            
            int q = 1;
            if(args.length > 1){
                try{
                    q = Integer.parseInt(args[1]);
                } catch(NumberFormatException e){
                    sender.sendMessage("Invalid quantity, please use a number.");
                }
            }
            Item i = new Item(material, q, this.getEngine(), new Location());
            sender.give(i);
            sender.sendMessage("Given " + i.toString() + " to " + sender.getName() +".");
    }
    
    private void giveAll(Player player, String[] args){
        int q = 1;
        if(args.length > 1){
            try{
                q = Integer.parseInt(args[1]);
            } catch(NumberFormatException e){
                player.sendMessage("Invalid quantity, please use a number.");
            }
        }
        
        for(Material mat : Material.values()){
            if(mat == Material.AIR) continue;
            player.give(new Item(mat, q, this.getEngine(), new Location()));
        }
        player.sendMessage("You have now " + q + " times all the game's items");
    }
}
