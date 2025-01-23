package engine;

import entities.LivingEntity;
/**
 * Décrivez votre classe UsableMaterial ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public interface ConsumableMaterial
{
    public boolean canConsume(LivingEntity target);
    public void consume(LivingEntity target);
}
