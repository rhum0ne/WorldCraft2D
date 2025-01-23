package gui;

import engine.GameEngine;
import engine.Seed;
import java.io.File;
import javax.swing.JTextField;

/**
 * Décrivez votre classe CreateButton ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CreateButton extends Button
{
    GameMenuPanel panel;
    
    /**
     * Constructeur d'objets de classe PlayButton
     */
    public CreateButton(GameMenuPanel panel, final GameEngine pEngine)
    {
        super(panel, pEngine, "create.png");
        this.panel = panel;
    }
     
    public void run(){
        String name = panel.getWorldPanel().getWorldName().replaceAll(" ", "_");
        if(name.equals("")) return;
        
        if(!exists(name)){
            panel.getWorldPanel().setInfo(panel.getWorldPanel().WORLD_DONT_EXISTS);
            this.getEngine().getGUI().setPlaying(true);
            
            JTextField enteredSeed = panel.getWorldPanel().getEnteredSeed();
            if(enteredSeed.getText().equals("")){
                if(this.getEngine().getWorld() == null) this.getEngine().play(name, Seed.random());
            }
            else{
                if(this.getEngine().getWorld() == null) this.getEngine().play(name, Seed.create(enteredSeed.getText()));
            }
        } else{
            panel.getWorldPanel().setInfo(panel.getWorldPanel().WORLD_EXISTS);
        }
        this.panel.set(panel.getMainMenuPanel());
    }
    
    private boolean exists(String name){
        File save = new File("saves\\" + name);
        return save.exists();
    }
}
