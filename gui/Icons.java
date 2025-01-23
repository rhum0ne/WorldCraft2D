package gui;
import javax.swing.ImageIcon;
import java.net.URL;

import engine.Game;
/**
 * Enumeration Icons - écrire ici la description de l'énumération
 *
 * @author (votre nom)
 * @version (numéro de version ou date)
 */
public enum Icons
{
    LIFE(loadIcon("life_empty"), loadIcon("life_semi"), loadIcon("life_full")),
    HUNGER(loadIcon("hunger_empty"), loadIcon("hunger_semi"), loadIcon("hunger_full"));
    
    public ImageIcon EMPTY;
    public ImageIcon SEMI;
    public ImageIcon FULL;
    
    Icons(ImageIcon empty, ImageIcon semi, ImageIcon full){
        this.EMPTY = empty;
        this.SEMI = semi;
        this.FULL = full;
    }
    
    private static ImageIcon loadIcon(final String path){
        URL vImageURL = Game.getInstance().getClass().getClassLoader().getResource( "assets\\gui\\bars\\" + path + ".png" );
        if ( vImageURL == null )
        vImageURL = Game.getInstance().getClass().getClassLoader().getResource( "assets\\placeholder.png" );
        return new ImageIcon( vImageURL );
    }
}
