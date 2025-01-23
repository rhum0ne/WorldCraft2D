package engine;

import entities.LivingEntity;
/**
 * Décrivez votre classe ToolData ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class KnifeData extends MaterialData implements MeleeWeapon
{
    
    public KnifeData(){
        super(false, false, false);
    }
    
    public int getDamages(){ return 9; }
    public boolean isLootDoubled(){ return true; }
}
