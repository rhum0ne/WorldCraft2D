package key;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import engine.GameEngine;

/**
 * The KeyboardListener class implements key event handling for the game engine.
 * 
 * @version 1.0
 */
public class KeyboardListener implements KeyListener, Runnable
{
    private GameEngine aEngine;
    private List<Keys> aPressedKeys;

    /**
     * Constructs a KeyboardListener object.
     * 
     * @param pEngine The game engine.
     */
    public KeyboardListener(final GameEngine pEngine)
    {
        this.aEngine = pEngine;
        this.aPressedKeys = new ArrayList<>();
    }
    
    /**
     * Handles key press events.
     * 
     * @param pE The key event.
     */
    @Override
    public void keyPressed(KeyEvent pE){
        //System.out.println(pE);
        if(this.aEngine.getWorld() == null) return;
        if(Keys.exists(pE.getKeyCode()) && !this.aPressedKeys.contains(Keys.getKey(pE.getKeyCode()))){
            Keys key = Keys.getKey(pE.getKeyCode());
            
            key.press(aEngine.getPlayer());
            this.aPressedKeys.add(key);
        }
        this.run();
    }
    
    /**
     * Handles key release events.
     * 
     * @param pE The key event.
     */
    @Override
    public void keyReleased(KeyEvent pE){
        if(this.aEngine.getWorld() == null) return;
        if(Keys.exists(pE.getKeyCode())){
            Keys key = Keys.getKey(pE.getKeyCode());
            
            if(this.aPressedKeys.contains(key)){
                key.release(aEngine.getPlayer());
                this.aPressedKeys.remove(key);
            }
        }
    }
    
    /**
     * Handles key typed events.
     * 
     * @param pE The key event.
     */
    @Override
    public void keyTyped(KeyEvent pE){
    }
    
    public void run(){
        for(Keys key : this.aPressedKeys){
            if(key.isRepeatable()){
                key.press(aEngine.getPlayer());
            }
        }
    }
}
