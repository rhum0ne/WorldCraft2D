package gui;

import engine.GameEngine;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 * Décrivez votre classe Button ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class Button extends JButton implements Runnable
{
    private GameEngine aEngine;
    
    private String imagePath;
    
    public Button(final GameMenuPanel panel, final GameEngine pEngine, final String pText){
        super();
        
        this.aEngine = pEngine;
        this.imagePath = pText;
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.addActionListener(panel);
        this.setImage();
    }
    
    public GameEngine getEngine(){ return this.aEngine; }
    
    /**
     * Set the image of the button.
     *
     * @param pImageName The name of the image file to set as the button's icon.
     */
    public void setImage(){
        String vImagePath = "assets\\gui\\" + this.imagePath; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            //this.setBounds(this.getX(), this.getY(), vIcon.getIconWidth(), vIcon.getIconHeight());
            this.setIcon( vIcon );
        }
    }
}
