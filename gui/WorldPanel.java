package gui;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLayeredPane;

import engine.*;
/**
 * Décrivez votre classe WorldPanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldPanel extends JLayeredPane
{
    private GameEngine aEngine;
    private List<Block> aBlocks;
    private WorldEventsListener eventsListener;
    
    private int leftDistance;
    private int rightDistance;
    public WorldPanel(GameEngine pEngine, UserInterface pGUI){
        this.aEngine = pEngine;
        this.aBlocks = new ArrayList<>();
        this.setBounds(0, 0, 10000, 3200);
        this.eventsListener = new WorldEventsListener(pEngine);
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(true);
    }
    
    /*public void replace(){
        //int w = this.getParent().getParent().getWidth();
        int h = this.getParent().getParent().getHeight();
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }*/

    public void addX(final int pix){
        this.setBounds(this.getX()+pix, this.getY(), this.getWidth(), this.getHeight());
    }
    
    public void addY(final int pix){
        this.setBounds(this.getX(), this.getY()+pix, this.getWidth(), this.getHeight());
    }
    
    public List<Block> getBlocks(){ return this.aBlocks; }
    public WorldEventsListener getEventListener(){ return this.eventsListener; }
    
    public void createBlocks(final int number){
        if(number == 0) return;
        
        int start = rightDistance;
        if(number < 0) start = leftDistance;
        int end = start + number;
        if(number > 0)
            for(int x=start; x<end; x++){
                createBlock(x);
            }
            
        else if(number < 0)
            for(int x=start; x>end; x--){
                createBlock(x);
            }
    }
    
    private void createBlock(final int x){
        for(int y=0; y<=32; y++){
            Block block = new Block(this.aEngine, this.eventsListener, x, y);
            this.add(block, DEFAULT_LAYER);
            this.aBlocks.add(block);
        }
    }
}
