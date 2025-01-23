package gui;

import entities.Item;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.net.URL;
/**
 * Décrivez votre classe ItemInfoPanel ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ItemInfoPanel extends JPanel
{
    
    private final JTextArea aItemName;
    private final JTextArea aItemQuantity;
    
    /**
     * Constructeur d'objets de classe ItemInfoPanel
     */
    public ItemInfoPanel()
    {
        super();
        
        this.setLayout(null);
        this.setBounds(0, 0, 125, 80);
        this.setBackground(new Color(0, 0, 0, 0));
        this.aItemName = new JTextArea("null");
        this.aItemName.setBounds(4, 5, 115, 17);
        this.aItemName.setBorder(null);
        this.aItemName.setOpaque(false);
        this.aItemName.setForeground(Color.WHITE);
        this.add(aItemName);
        
        this.aItemQuantity = new JTextArea("Quantity: null");
        this.aItemQuantity.setBounds(4, 40, 115, 17);
        this.aItemQuantity.setBorder(null);
        this.aItemQuantity.setOpaque(false);
        this.aItemQuantity.setForeground(Color.WHITE);
        this.add(aItemQuantity);
        
        this.setVisible(false);
    }
    
    public void setPos(final int pX, final int pY){
        this.setBounds(pX, pY, this.getWidth(), this.getHeight());
    }
    
    public void setItem(final Item pItem){
        this.aItemName.setText(pItem.getName());
        this.aItemQuantity.setText("Quantity: " + pItem.getQuantity());
        
        //Ne marche pas, sans doute car les composants ont des tailles fixes.
        /*int vWidth = 0;
        int vHeight = 0;
        for(Component c : this.getComponents()){
            vHeight += c.getHeight();
            vWidth = Math.max(vWidth, c.getWidth());
        }
        this.setBounds(this.getX(), this.getY(), vWidth, vHeight);*/
    }
    
    /**
     * Custom painting method to draw the background image on the panel.
     *
     * @param pGraph The graphics context to paint.
     */
    @Override
    public void paintComponent(Graphics pGraph){
        Image icon;
        try{
            icon = ImageIO.read(new File("assets\\gui\\item-info.png"));
        } catch(IOException e){
            System.out.println(e);
            return;
        }
        super.paintComponent(pGraph);
        
        pGraph.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
