package engine;

import entities.LivingEntity;
/**
 * Décrivez votre classe OpenedTrapMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class OpenedTrapMaterial extends MaterialData implements InteractableMaterial
{
    public OpenedTrapMaterial(){
        super(true, true, true, false);
    }
    
    @Override
    public void onInteract(LivingEntity e, Block bloc){
        if(!(bloc.getType().getData() instanceof OpenedTrapMaterial)) return;
        
        bloc.setBlock(Material.CLOSED_TRAP);
    }
}
