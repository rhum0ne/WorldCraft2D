package engine;

import entities.LivingEntity;
/**
 * Décrivez votre interface UsableMaterial ici.
 *
 * @author  (votre nom)
 * @version (un numéro de version ou une date)
 */

public interface UsableMaterial
{
    public void applyLeftClickEffect(Block block, LivingEntity target);
}
