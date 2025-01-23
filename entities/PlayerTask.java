package entities;
import java.util.TimerTask;


/**
 * Décrivez votre classe PlayerTask ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PlayerTask extends TimerTask
{
    private Player player;
    private int s = 10;
    
    public PlayerTask(Player player){
        this.player = player;
    }
    
    @Override
    public void run(){
        if(player.getSaturation().isMin() && !player.isInGodMode()){
            player.getHunger().remove(1);
            player.getSaturation().setMax();
        }
        s--;
        if(s==0){
            s=10;
            this.all5Seconds();
        }
    }
    
    private void all5Seconds(){
        player.getSaturation().remove(20);
        if(player.getHunger().isMin()){
            player.makeDamages(2);
        }
        else if(!player.getLife().isMax() && player.getHunger().get() > 14){
            player.getLife().add(1);
            player.getSaturation().remove(50);
        }
    }
}
