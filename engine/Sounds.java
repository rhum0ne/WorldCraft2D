package engine;

/**
 * The Sounds enumeration defines the paths for various sound effects used in the game.
 * 
 * @version 1.0
 */
public enum Sounds {
    EAT("eat.wav"),
    DRINK("drink.wav"),
    HURT("classic_hurt.wav"),
    DIRT("grass.wav"),
    ROCK("stone.wav"),
    WOOD("wood.wav"),
    SAND("sand.wav"),
    SNOW("snow.wav");
    
    private String aPath;
    
    /**
     * Constructs a Sounds enum with the given file path.
     * 
     * @param pPath The file path for the sound effect.
     */
    Sounds(final String pPath){
        this.aPath = pPath;
    }
    
    /**
     * Retrieves the file path associated with the sound effect.
     * 
     * @return The file path of the sound effect.
     */
    public String getPath(){ 
        return this.aPath; 
    }
}
