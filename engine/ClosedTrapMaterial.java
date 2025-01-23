package engine;

import entities.LivingEntity;
/**
 * Décrivez votre classe TrapMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ClosedTrapMaterial extends MaterialData implements InteractableMaterial
{
    public ClosedTrapMaterial(){
        super(true, true, true, true);
    }
    
    
    @Override
    public void onInteract(LivingEntity e, Block bloc){
        if(!(bloc.getType().getData() instanceof ClosedTrapMaterial)) return;
        
        bloc.setBlock(Material.TRAP);
    }
}
