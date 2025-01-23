package engine;

import gui.UserInterface;
/**
 * Décrivez votre classe Game ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Game
{
    private static Game INSTANCE;
    
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     * Create the game and initialise its internal map. Create the inerface and link to it.
     */
    public Game() 
    {
        INSTANCE = this;
        
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
        
        //this.aEngine.play();
    }
    
    public static Game getInstance(){ return INSTANCE; }
}
