package gui;

import engine.GameEngine;
/**
 * Décrivez votre classe PlayButton ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class LoadWorldButton extends Button
{
    GameMenuPanel panel;
    /**
     * Constructeur d'objets de classe PlayButton
     */
    public LoadWorldButton(GameMenuPanel panel, final GameEngine pEngine)
    {
        super(panel, pEngine, "load.png");
        this.panel = panel;
    }
     
    public void run(){
        panel.set(panel.getWorldLoadingPanel());
    }
}
