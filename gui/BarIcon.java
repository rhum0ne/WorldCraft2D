package gui;

import javax.swing.JLabel;
import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * The BarIcon class represents an icon used to display the Player's statistics.
 * 
 * @version 1.0
 */
public class BarIcon extends JLabel
{
    private Bar aBar;
    
    /**
     * Constructs a BarIcon object.
     * 
     * @param pBar The bar associated with the icon.
     * @param pX The x-coordinate position of the icon.
     */
    public BarIcon(final Bar pBar, final int pX)
    {
        this.aBar = pBar;
        this.setBounds(pX * 32 + 16, 0, 32, 32);
    }
    
    /**
     * Sets the state of the bar icon.
     * 
     * @param pState The state to set for the bar icon.
     */
    public void setState(final ImageIcon pIcon){
        this.setIcon( pIcon );
    }
}
