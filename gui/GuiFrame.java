package gui;

import engine.GameEngine;
import engine.Material;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Décrivez votre classe GuiFrame ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GuiFrame extends JFrame
{
    
    public GuiFrame(final GameEngine pEngine){
        this.setTitle("WorldCraft 2D");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setIconImage(Material.GRASS.getTexture().getImage());

        setFocusable(true);
        requestFocus(); 
    }
}
