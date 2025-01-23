package engine;


/**
 * Décrivez votre classe OreVein ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public enum OreVein
{
    CALCITE(Material.CALCITE, 4, 11, 10),
    BASALT(Material.BASALT, 3, 9, 1);
    
    Material type;
    int min;
    int max;
    int spawnRate;
    OreVein(Material type, int min, int max, int rate){
       this.type = type;
       this.min = min;
       this.max = max;
       this.spawnRate = rate;
    }
    
    public Material getType(){ return this.type; }
    public int getMin(){ return this.min; }
    public int getMax(){ return this.max; }
    public int getSpawnRate(){ return this.spawnRate; }
}
