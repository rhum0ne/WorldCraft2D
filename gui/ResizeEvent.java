package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Décrivez votre classe ResizeEvent ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ResizeEvent extends ComponentAdapter
{
    private UserInterface aGUI;
    
    /**
     * Constructeur d'objets de classe ResizeEvent
     */
    public ResizeEvent(UserInterface pGUI)
    {
        this.aGUI = pGUI;
    }

    public void componentResized(ComponentEvent e){
        if(this.aGUI.getGamePanel() != null){
            if(this.aGUI.getEngine().isInGame())
                this.aGUI.getGamePanel().getInterfaces().replace();
        }
        //this.aPanel.getWorldPanel().replace();
    }
}
