package gui;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.GameEngine;
/**
 * Décrivez votre classe MainMenu ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class GameMenuPanel extends JPanel implements ActionListener
{
    private GameEngine aEngine;
    private UserInterface aGUI;
    private MainMenuPanel mainMenu;
    private WorldCreationPanel aWorldPanel;
    private WorldLoadPanel aWorldLoadPanel;
    private Image aBackground;
    
    public GameMenuPanel(GameEngine pEngine, UserInterface pGUI){
        this.aEngine = pEngine;
        this.aGUI = pGUI;
        this.setBackground(new Color(0, 0, 0, 0));
        try{
            this.aBackground = ImageIO.read(new File("assets\\gui\\font.png"));
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        this.mainMenu = new MainMenuPanel(this, pEngine, pGUI);
        this.aWorldPanel = new WorldCreationPanel(this, pEngine, pGUI);
        this.aWorldLoadPanel = new WorldLoadPanel(this, pEngine, pGUI);
        this.add(this.mainMenu);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() instanceof Button b){
            b.run();
            this.repaint();
        }
    }
    
    public MainMenuPanel getMainMenuPanel(){ return this.mainMenu; }
    
    public WorldLoadPanel getWorldLoadingPanel(){ return this.aWorldLoadPanel; }
    
    public WorldCreationPanel getWorldPanel(){ return this.aWorldPanel; }
    
    public void set(JPanel panel){
        this.removeAll();
        this.add(panel);
        this.repaint();
        this.revalidate();
    }
    
    
    /**
     * Custom painting method to draw the background image on the panel.
     *
     * @param pGraph The graphics context to paint.
     */
    @Override
    public void paintComponent(Graphics pGraph){
        super.paintComponent(pGraph);
        
        pGraph.drawImage(this.aBackground, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
