package gui;

import engine.GameEngine;
/**
 * Décrivez votre classe PlayButton ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PlayButton extends Button
{
    GameMenuPanel panel;
    /**
     * Constructeur d'objets de classe PlayButton
     */
    public PlayButton(GameMenuPanel panel, final GameEngine pEngine)
    {
        super(panel, pEngine, "create.png");
        this.panel = panel;
    }
     
    public void run(){
        panel.set(panel.getWorldPanel());
    }
}
