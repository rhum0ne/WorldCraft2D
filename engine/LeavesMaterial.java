package engine;


/**
 * Décrivez votre classe LeavesMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class LeavesMaterial extends MaterialData implements TickingBlock
{
    public static double ALEA = 0.6;
    
    public LeavesMaterial(){
        super(true, true, false);
    }
    
    public void tick(Block block){
        if(block.hasBlockInRange(Material.WOOD, 4)) return;
        
        if(Math.random() < 0.6){
            block.addTick();
            if(block.isTick(5))
                block.setBlock(Material.AIR);
        }
    }
}
