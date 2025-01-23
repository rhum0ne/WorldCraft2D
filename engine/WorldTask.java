package engine;
import java.util.TimerTask;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import entities.Pig;

/**
 * Décrivez votre classe WorldTask ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldTask extends TimerTask
{
    private GameEngine aEngine;
    private World aWorld;
    
    public WorldTask(final GameEngine pEngine, final World pWorld){
        this.aEngine = pEngine;
        this.aWorld = pWorld;
    }
    
    public void run(){
        if(!this.aEngine.isInGame()) return;
        aEngine.getWorld().addTime(1);
        
        Iterator<Block> iterator = this.aWorld.getBlocks().values().iterator();
        boolean spawn = false;
        double rdm = Math.random();
        Block bloc;
        try{
            while(iterator.hasNext()){
                if(this.aEngine.getWorld() == null) return;
                bloc = iterator.next();
                bloc.tick();
                if(bloc.getType() == Material.GRASS && bloc.getLocations().getDistanceFrom(this.aEngine.getPlayer().getLocations()) < 10){
                    if(rdm < 0.002 && this.aEngine.getEntitiesManager().getLivingEntities().size() < 6 && !spawn) {
                        this.aWorld.spawn(new Pig(this.aEngine, new Location(bloc.getLocations().getX(), bloc.getLocations().getY()-1)));
                        spawn = true;
                    }
                }
            }
        }catch(ConcurrentModificationException e){
            
        }
    }
}
