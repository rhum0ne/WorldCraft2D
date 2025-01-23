package engine;

import java.util.TimerTask;
import java.util.ConcurrentModificationException;
import entities.Entity;
import java.util.Iterator;

/**
 * Décrivez votre classe GameTask ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GameTask extends TimerTask {
    private GameEngine aEngine;
    
    /**
     * Constructs a GameTask with the given game engine.
     * 
     * @param pEngine The game engine.
     */
    public GameTask(final GameEngine pEngine){
        this.aEngine = pEngine;
    }
    
    /**
     * Runs the game task.
     */
    @Override
    public void run(){
        if(!this.aEngine.isInGame()) return;
        Iterator<Entity> iterator = this.aEngine.getEntitiesManager().getEntities().iterator();
        
        try{
            while(iterator.hasNext()){
                //if(this.aEngine.getWorld() == null) return;
                Entity entity = iterator.next();
                entity.run();
                //this.aEngine.getGUI().repaint();
            }
        }catch(ConcurrentModificationException e){
            
        }
    }
}
