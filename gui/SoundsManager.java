package gui;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.HashMap;

import engine.Sounds;

/**
 * The SoundsManager class manages audio playback for the game.
 * 
 * @version 1.0
 */
public class SoundsManager {

    private HashMap<Sounds, Clip> aSounds;
    
    /**
     * Constructs a SoundsManager and loads all audio files.
     */
    public SoundsManager() {
        this.aSounds = new HashMap<>();
        
        for(Sounds vSound : Sounds.values()){
            try{
                // Get the audio input stream
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("assets\\sounds\\" + vSound.getPath()).getAbsoluteFile());
                
                // Open the clip and load the audio stream
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                
                // Store the clip in the map
                this.aSounds.put(vSound, clip);
            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
                System.out.println("Problem during loading " + vSound.getPath());
                System.out.println(ex.getMessage());
            }
        }
    }
  
    /**
     * Retrieves the Clip associated with the given sound.
     * 
     * @param pSound The sound enum.
     * @return The Clip associated with the sound.
     */
    public Clip get(final Sounds pSound){ 
        return this.aSounds.get(pSound);
    }
}
