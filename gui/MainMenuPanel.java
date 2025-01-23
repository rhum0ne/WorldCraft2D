package gui;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import engine.GameEngine;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
/**
 * Décrivez votre classe Menu ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class MainMenuPanel extends JPanel
{
    private Button play;
    private Button load;
    private GameEngine aEngine;
    private UserInterface aGUI;
    
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    
    public MainMenuPanel(GameMenuPanel panel, GameEngine pEngine, UserInterface pGUI){
        super();
        
        this.aEngine = pEngine;
        this.aGUI = pGUI;
        
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(new BorderLayout());
        
        this.topPanel = new JPanel();
        this.topPanel.setLayout(new FlowLayout());
        this.topPanel.setPreferredSize(new Dimension(1, 350));
        this.topPanel.setBackground(new Color(0, 0, 0, 0));
        this.add(topPanel, BorderLayout.NORTH);
        
        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new FlowLayout());
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.bottomPanel.setBackground(new Color(0, 0, 0, 0));
        this.bottomPanel.setSize(1, 200);
        
        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        this.centerPanel.setBackground(new Color(0, 0, 0, 0));
        this.add(centerPanel, BorderLayout.CENTER);
        
        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new FlowLayout());
        this.leftPanel.setBackground(new Color(0, 0, 0, 0));
        this.leftPanel.setPreferredSize(new Dimension(200, 1));
        this.add(leftPanel, BorderLayout.EAST);
        
        this.rightPanel = new JPanel();
        this.rightPanel.setLayout(new FlowLayout());
        this.rightPanel.setBackground(new Color(0, 0, 0, 0));
        this.rightPanel.setPreferredSize(new Dimension(200, 1));
        this.add(rightPanel, BorderLayout.WEST);
        
        JLabel title = new JLabel();
        this.setImage(title, "title.png");
        this.topPanel.add(title);
        
        this.play = new PlayButton(panel, pEngine);
        centerPanel.add(play);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        this.load = new LoadWorldButton(panel, pEngine);
        centerPanel.add(load);
    }
    
    public void setImage(JLabel a, String path){
        String vImagePath = "assets\\gui\\" + path; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            a.setBounds(this.getX(), this.getY(), vIcon.getIconWidth(), vIcon.getIconHeight());
            a.setIcon( vIcon );
        }
    }
    
}
