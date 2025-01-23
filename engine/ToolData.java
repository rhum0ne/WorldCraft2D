package engine;

import entities.LivingEntity;
/**
 * Décrivez votre classe ToolData ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ToolData extends MaterialData implements UsableMaterial, MeleeWeapon
{
    private Type aType;
    private int damages;
    
    public ToolData(Type pType, int damages){
        super(false, false, false);
        this.damages = damages;
        this.aType = pType;
    }
    
    public int getDamages(){ return this.damages; }
    public boolean isLootDoubled(){ return false; }
    
    public boolean hasEffectOn(Material material){
        return material.getType() == this.aType;
    }
    
    public boolean hasEffectOn(Block block){
        return this.hasEffectOn(block.getType());
    }
    
    public void applyRightClickEffect(Block block, LivingEntity target){ }
    
    public void applyLeftClickEffect(Block block, LivingEntity target){ }
}
