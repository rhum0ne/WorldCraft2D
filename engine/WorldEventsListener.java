package engine;

import java.awt.event.*;
import javax.swing.JButton;
import entities.Player;
import entities.Item;
import java.awt.Component;

import entities.LivingEntity;

/**
 * Décrivez votre classe MouseEvent ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldEventsListener extends MouseAdapter
{
    private GameEngine aEngine;
    
    public WorldEventsListener(GameEngine pEngine)
    {
        this.aEngine = pEngine;
    }
    
    public void mouseEntered(MouseEvent pMe){
        if(!(pMe.getSource() instanceof Block bloc)) return;
        if(aEngine.getPlayer().getLocations().getDistanceFrom(bloc.getLocations()) > aEngine.getPlayer().getReach()) {
            this.aEngine.getGUI().getFrame().requestFocus();
            return;
        }
        bloc.setBorderPainted(true);
    }
    
    public void mouseExited(MouseEvent pMe){
        if(!(pMe.getSource() instanceof Block bloc)) return;
        bloc.setBorderPainted(false);
    }
    
    public void mousePressed(MouseEvent pMe) {
        Player player = aEngine.getPlayer();
        if(pMe.getButton() == MouseEvent.BUTTON1){
            if(player.hasItemInHand() && !(pMe.getSource() instanceof Item)){
                Item item = player.getItemInHand();
                
                if(pMe.getSource() instanceof Block bloc){
                    MaterialData data = item.getType().getData();
                    MaterialData blockData = bloc.getType().getData();
                    if(blockData instanceof InteractableMaterial){
                        player.interact(bloc);
                    }
                    if(data instanceof ConsumableMaterial tData){
                        if(tData.canConsume(player)){
                            tData.consume(player);
                            player.getInventory().removeItem(item, 1);
                        }
                    }
                    else if(data instanceof ToolData tData){
                        if(!tData.hasEffectOn(bloc)){
                            tData.applyRightClickEffect(bloc, player);
                        }
                    }
                    else if(item.getType().getData().isPlaceable() && bloc.getType() == Material.AIR)
                        this.placeBlock(player, bloc, item);
                }
                
            } else
                this.handRightClick(player, pMe.getSource());
        }
        else {
            if(player.hasItemInHand() && !(pMe.getSource() instanceof Item)){
                Item item = player.getItemInHand();
                
                if(pMe.getSource() instanceof Block bloc){
                    MaterialData data = item.getType().getData();
                    if(data instanceof ToolData tData){
                        tData.applyLeftClickEffect(bloc, player);
                    }
                    else if(bloc.getType().getData().isBreakable() && bloc.getType() != Material.AIR)
                        this.handLeftClick(player, pMe.getSource());
                }
                
                if(pMe.getSource() instanceof LivingEntity entity){
                    if(player.equals(entity)) return;
                    player.hit(entity);
                }
                
            } else
                this.handLeftClick(player, pMe.getSource());
        }
        this.aEngine.getGUI().getFrame().requestFocus(); 
    }   
    
    private void placeBlock(Player player, Block bloc, Item item){
        if(aEngine.getPlayer().getLocations().getDistanceFrom(bloc.getLocations()) > player.getReach())
            return;

        player.getInventory().removeItem(item);
        bloc.setBlock(item.getType());
        player.playSound(item.getType().getSound());
    }
    
    private void handRightClick(Player player, Object source){
        if(source instanceof Block bloc){
            MaterialData data = bloc.getType().getData();
            if(data instanceof InteractableMaterial tData){
                player.interact(bloc);
            }
        }
        
        else if(source instanceof Item item){
            if(player.getLocations().getDistanceFrom(item.getLocations()) > player.getReach())
                return;
            
            player.take(item);
        }
    }
    
    private void handLeftClick(Player player, Object source){
        if(source instanceof Block bloc){
            if(player.canReach(bloc)){
                player.playSound(bloc.getType().getSound());
                bloc.destroy();
            }
        }
        
        if(source instanceof LivingEntity entity){
            if(player.equals(entity)) return;
            player.hit(entity);
        }
    }
}
