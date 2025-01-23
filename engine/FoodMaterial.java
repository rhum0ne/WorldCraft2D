package engine;

import entities.LivingEntity;
import entities.Player;
/**
 * Décrivez votre classe FoodMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class FoodMaterial extends MaterialData implements ConsumableMaterial
{
    private int healing;
    private int hunger;
    private int saturation;
    
    public FoodMaterial(int hunger, int saturation, int healing){
        super(false, false, false, false);
        this.healing = healing;
        this.hunger = hunger;
        this.saturation = saturation;
    }
    public FoodMaterial(int hunger, int saturation){
        super(false, false, false, false);
        this.healing = 0;
        this.hunger = hunger;
        this.saturation = saturation;
    }
    
    public boolean canConsume(LivingEntity e){
        if(!e.getLife().isMax()) return true;
        else return (e instanceof Player p) && !(p.getHunger().isMax());
    }
    
    public void consume(LivingEntity target){
        target.getLife().add(this.healing);
        if(target instanceof Player p){
            p.playSound(Sounds.EAT);
            p.getHunger().add(hunger);
            p.getSaturation().add(saturation);
        }
    }
}
