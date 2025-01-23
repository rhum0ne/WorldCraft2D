package gui;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import engine.GameEngine;
/**
 * Décrivez votre classe WorldCreationPanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldLoadPanel extends JPanel implements ActionListener
{
    
    private GameEngine aEngine;
    private UserInterface aGUI;
    private GameMenuPanel aMenu;

    private JPanel panel;
    
    List<WorldButton> buttons;
    /**
     * Constructeur d'objets de classe WorldCreationPanel
     */
    public WorldLoadPanel(GameMenuPanel menu, GameEngine pEngine, UserInterface pGUI){
        super();
        
        this.aEngine = pEngine;
        this.aMenu = menu;
        this.aGUI = pGUI;
        this.setBackground(new Color(0, 0, 0, 0));
        
        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
        
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.add(Box.createRigidArea(new Dimension(0, 75)));
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(0,1, 0, 5));
        grid.setBackground(new Color(0, 0, 0, 0));
        
        JScrollPane vListScroller = new JScrollPane( grid );
        vListScroller.setBackground(new Color(0, 0, 0, 0));
        vListScroller.getViewport().setOpaque(false);
        vListScroller.setMaximumSize( new Dimension(420, 650) );
        vListScroller.setMinimumSize( new Dimension(420, 650) );
        vListScroller.setBorder(null);
        vListScroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                revalidate();
            }
        });
        panel.add(vListScroller);
        
        File dir = new File("saves\\");
        for(File worldFile : dir.listFiles() ){
            WorldButton b = new WorldButton(worldFile.getName(), this);
            grid.add(b);
        }
        
    }
    
    public void actionPerformed(ActionEvent pE){
        if(pE.getSource() instanceof WorldButton b){
            this.aEngine.getGUI().setPlaying(true);
            this.aEngine.play(b.getWorldName(), null);
            aMenu.set(aMenu.getMainMenuPanel());
        }
    }
}
