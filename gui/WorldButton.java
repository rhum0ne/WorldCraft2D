package gui;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;


/**
 * Décrivez votre classe WorldButton ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldButton extends JButton
{
    private String worldName;
    
    public WorldButton(String name, WorldLoadPanel panel){
        super(name.replaceAll("_", " "));
        this.setPreferredSize((new Dimension(400, 50)));
        this.addActionListener(panel);
        this.setBackground(Color.GRAY);
        this.setForeground(Color.BLACK);
        this.worldName = name;
    }
    
    public String getWorldName(){ return this.worldName; }
}
