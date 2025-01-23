package gui;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;

import engine.GameEngine;
/**
 * Décrivez votre classe WorldCreationPanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldCreationPanel extends JPanel
{   
    public static String WORLD_EXISTS = "A world already have this name !";
    public static String WORLD_DONT_EXISTS = "";
    
    private GameEngine aEngine;
    private UserInterface aGUI;

    private JPanel panel;
    private JTextField worldName;
    private JTextField seed;
    private JTextField info;
    private Button createWorld;
    /**
     * Constructeur d'objets de classe WorldCreationPanel
     */
    public WorldCreationPanel(GameMenuPanel menu, GameEngine pEngine, UserInterface pGUI){
        super();
        
        this.aEngine = pEngine;
        this.aGUI = pGUI;
        this.setBackground(new Color(0, 0, 0, 0));
        
        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.add(Box.createRigidArea(new Dimension(0, 150)));
        
        this.worldName = new JTextField();
        this.panel.add(worldName);
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        this.seed = new JTextField();
        this.panel.add(seed);
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        this.info = new JTextField();
        this.info.setBackground(new Color(0, 0, 0, 0));
        this.info.setBorder(null);
        this.info.setEditable(false);
        this.panel.add(info);
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        this.createWorld = new CreateButton(menu, pEngine);
        panel.add(createWorld);
        
        this.add(panel);
    }
    
    public void setInfo(final String info){ this.info.setText(info); }
    
    public String getWorldName(){
        return this.worldName.getText();
    }
    
    public String getEnteredSeedString(){
        return this.seed.getText();
    }
    
    public JTextField getEnteredSeed(){
        return this.seed;
    }
}
