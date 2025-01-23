package engine;

import entities.LivingEntity;
import entities.Item;
/**
 * Décrivez votre classe AxeData ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickaxeData extends ToolData
{
    /**
     * Constructeur d'objets de classe AxeData
     */
    public PickaxeData()
    {
        super(Type.ROCK, 3);
    }
    
    @Override
    public void applyLeftClickEffect(Block block, LivingEntity player){
        player.getEngine().getWorld().spawn(new Item(block.getType(), 1, player.getEngine(), new Location(block.getLocations())));
        block.destroy();
    }
}
