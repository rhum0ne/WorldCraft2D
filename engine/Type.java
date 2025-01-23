package engine;


/**
 * Enumeration Types - écrire ici la description de l'énumération
 *
 * @author (votre nom)
 * @version (numéro de version ou date)
 */
public enum Type
{
    ROCK(Sounds.ROCK),
    WOOD(Sounds.WOOD),
    FOOD(Sounds.ROCK),
    TOOL(Sounds.ROCK),
    PLANT(Sounds.DIRT),
    DECORATION(Sounds.ROCK),
    DIRT(Sounds.DIRT);
    
    Sounds sound;
    Type(Sounds s){
        this.sound = s;
    }
    
    public Sounds getSound(){ return this.sound; }
}
