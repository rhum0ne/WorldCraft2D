package entities;

import engine.GameEngine;
import engine.Location;
/**
 * Décrivez votre classe PassiveEntity ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PassiveEntity extends LivingEntity
{
    private static int PANIC_TIME = 2000;
    
    private int panicTime = 0;
    
    /**
     * Constructeur d'objets de classe PassiveEntity
     */
    public PassiveEntity(GameEngine pEngine, Location pLoc, int speed, int reach, int x, int y)
    {
        super(null, pEngine, pLoc, speed,reach, x, y);
    }
    
    public void setPanic(final boolean state){
        if(state) { 
            if(!this.isPanic()) this.setSpeed(this.getSpeed()*2);
            this.panicTime = PANIC_TIME;
        }
        else this.panicTime = 0;
    }
    
    public boolean isPanic(){
        return this.panicTime != 0;
    }
    
    @Override
    public void move(){
        if(this.isPanic()) this.panic();
        super.move();
    }
    
    private void panic(){
        this.panicTime--;
        if(!this.isPanic()) this.setSpeed(this.getSpeed()/2);
    }
}
