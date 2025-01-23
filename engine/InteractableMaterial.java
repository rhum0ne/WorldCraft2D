package engine;

import entities.LivingEntity;
/**
 * Décrivez votre classe InteractableMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public interface InteractableMaterial
{
    public abstract void onInteract(LivingEntity e, Block block);
}
