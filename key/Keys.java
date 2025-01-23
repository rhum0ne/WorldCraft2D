package key;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import key.*;
import entities.Player;
/**
 * The Key enum represents the keys used in the game.
 * 
 * @version 1.0
 */
public enum Keys
{
    FORWARD(new ForwardKey()),
    BACKWARD(new BackwardKey()),
    JUMP(new JumpKey()),
    ENTER(new EnterKey()),
    INVENTORY(new InventoryKey()),
    SPRINT(new SprintKey()),
    ESCAPE(new EscapeKey()),
    SAVE(new SaveKey());
    
    static HashMap<Integer, Keys> KEYS = new HashMap<>();
    
    Key function;
    
    Keys(Key function){
        this.function = function;
    }
    /**
     * Creates the key mapping.
     */
    public static void createKeys() {
        // Initialize the hash map to prevent conflicts with key codes.
        for(int i = 0; i < 150; i++) {
            KEYS.put(i, null);
        }
        
        // Map key codes to keys.
        KEYS.put(KeyEvent.VK_D, FORWARD);
        KEYS.put(KeyEvent.VK_UP, FORWARD);
        KEYS.put(KeyEvent.VK_Q, BACKWARD);
        KEYS.put(KeyEvent.VK_DOWN, BACKWARD);
        KEYS.put(KeyEvent.VK_SHIFT , SPRINT);
        KEYS.put(KeyEvent.VK_SPACE , JUMP);
        KEYS.put(KeyEvent.VK_ENTER , ENTER);
        KEYS.put(KeyEvent.VK_E, INVENTORY);
        KEYS.put(KeyEvent.VK_ESCAPE, ESCAPE);
        KEYS.put(KeyEvent.VK_RIGHT, ESCAPE);
        KEYS.put(KeyEvent.VK_T, SAVE);
    }
    
    /**
     * Gets the key associated with the given key code.
     * 
     * @param pKey The key code.
     * @return The corresponding Key enum value, or null if not found.
     */
    public static Keys getKey(int pKey){
        return KEYS.get(pKey);
    }
    
    public void press(Player player){
        this.function.onKeyPressed(player);
    }
    
    public void release(Player player){
        this.function.onKeyReleased(player);
    }
    
    public boolean isRepeatable(){ return this.function.isRepeatable(); }
    
    /**
     * Checks if a key with the given key code exists.
     * 
     * @param pKey The key code to check.
     * @return True if the key exists, false otherwise.
     */
    public static boolean exists(int pKey) { 
        return KEYS.containsKey(pKey) && KEYS.get(pKey) != null; 
    }
}
