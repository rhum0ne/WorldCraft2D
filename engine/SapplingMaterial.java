package engine;


/**
 * Décrivez votre classe LeavesMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SapplingMaterial extends MaterialData implements TickingBlock
{
    private static double ALEA = 0.1;
    
    public SapplingMaterial(){
        super(true, true, true, false);
    }
    
    public void tick(Block block){
        if(Math.random() < ALEA){
            block.addTick();
            if(block.isTick(20))
                block.getEngine().getWorld().spawn(Structures.TREE, block.getLocations());
        }
    }
}
