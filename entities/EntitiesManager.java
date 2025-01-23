package entities;

import java.util.HashMap;
import java.util.UUID;
import java.util.Collection;

/**
 * Décrivez votre classe Entities ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class EntitiesManager
{
    private HashMap<UUID, Entity> aEntities;
    private HashMap<UUID, LivingEntity> aLEntities;

    /**
     * Constructeur d'objets de classe Entities
     */
    public EntitiesManager()
    {
        this.aEntities = new HashMap<>();
        this.aLEntities = new HashMap<>();
    }
    
    public void reset(){ this.aEntities.clear(); }

    public Collection<Entity> getEntities(){ return this.aEntities.values(); }
    public Collection<LivingEntity> getLivingEntities(){ return this.aLEntities.values(); }
    public Entity getEntity(UUID pID){ return this.aEntities.get(pID); }
    
    public void add(Entity pEntity){ 
        this.aEntities.put(pEntity.getID(), pEntity); 
        if(pEntity instanceof LivingEntity) 
            this.aLEntities.put(pEntity.getID(), (LivingEntity)pEntity); 
    }
}
