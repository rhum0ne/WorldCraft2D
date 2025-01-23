package gui;

import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.net.URL;

import entities.EntityConstant;

/**
 * The Bar class represents a graphical bar in the game interface.
 * 
 * @version 1.0
 */
public class Bar extends JPanel
{
    private List<BarIcon> aIcons;
    private Icons aIcon;
    private EntityConstant aConstant;

    /**
     * Constructs a Bar object.
     * 
     * @param pPath The path of the bar icon images.
     * @param pConstant The entity constant associated with the bar.
     * @param pHeight The height of the bar.
     */
    public Bar(final Icons icons, final EntityConstant pConstant, final int pHeight)
    {
        this.aConstant = pConstant;
        this.aIcons = new ArrayList<>();
        this.aIcon = icons;
        
        this.setBounds(0, 16 + 42 * pHeight, 1000, 32);
        this.setLayout(null);
        this.setOpaque(false);
        
        createIcons();
    }
    
    /**
     * Sets the icons for the bar.
     */
    private void createIcons(){
        this.removeAll();
        this.aIcons.clear();
        for(int i = 0; i < this.aConstant.getMax() / 2; i++){
            BarIcon vIcon = new BarIcon(this, i);
            this.aIcons.add(vIcon);
            this.add(vIcon);
        }
        update();
    }
    
    /**
     * Sets the path of the bar icon images.
     * 
     * @param pPath The path of the bar icon images.
     */
    public void setIcons(final Icons icons){
        this.aIcon = icons;
    }

    /**
     * Updates the bar icons based on the current value of the associated entity constant.
     */
    public void update(){
        this.setVisible(false);
        int vCounter = this.aConstant.get();
        for(BarIcon icon : this.aIcons){
            if(vCounter == 0) 
                icon.setIcon(this.aIcon.EMPTY);
            else if(vCounter == 1){
                icon.setIcon(this.aIcon.SEMI);
                vCounter--;
            } else{
                icon.setIcon(this.aIcon.FULL);
                vCounter -= 2;
            }
        }
        this.setVisible(true);
    }
}
