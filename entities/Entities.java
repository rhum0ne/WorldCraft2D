package entities;
import java.util.HashMap;
import javax.swing.ImageIcon;
import java.net.URL;
import engine.Game;
/**
 * Enumeration Textures - écrire ici la description de l'énumération
 *
 * @author (votre nom)
 * @version (numéro de version ou date)
 */
public enum Entities
{
    PIG("pig.png"),
    PLAYER("player.png");
    
    private static HashMap<Entities, ImageIcon> TEXTURES = new HashMap<>();
    
    
    String aTexture;
    Entities(String pTexture){
        this.aTexture = pTexture;
    }
    
    public static void loadTextures(){
        for(Entities e : Entities.values()){
            URL vImageURL = Game.getInstance().getClass().getClassLoader().getResource( "assets\\entities\\" + e.aTexture );
            if ( vImageURL == null )
                vImageURL = Game.getInstance().getClass().getClassLoader().getResource( "assets\\placeholder.png" );
            TEXTURES.put(e, new ImageIcon( vImageURL ));
        }
    }
    
    public ImageIcon getTexture(){ return TEXTURES.get(this); }
}
