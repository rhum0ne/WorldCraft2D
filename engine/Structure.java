package engine;
import java.util.HashMap;


/**
 * Décrivez votre classe Structure ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Structure
{
    private HashMap<Character, Material> mat;
    private char[][] schem;
    
    private Material[][] aSchematic;
    
    public Structure(){
        mat = new HashMap<>();
    }
    
    public void setMaterial(char letter, Material mat){ this.mat.put(letter, mat); }
    public void setSchem(char[][] schem) {this.schem = schem;}
    
    public void validate(){
        if(this.schem == null) return;
        
        this.aSchematic = new Material[schem.length][schem[0].length];
        
        for(int x=0; x<schem.length; x++){
            for(int y=0; y<schem[x].length; y++){
                char letter = this.schem[x][y];
                if(!this.mat.containsKey(letter)) {
                    System.out.println("Error during loading of " + this + "\n Letter " + letter + " not defined.");
                    return;
                }
                this.aSchematic[x][y] = this.mat.get(letter);
            }
        }
    }
    
    public void build(World world, Location loc){
        if(this.aSchematic == null){
            System.out.println("Error during build of " + this + "\n Schematic not created.");
            return;
        }
        int pXStart = (int)loc.getX()-this.aSchematic.length/2+1;
        int pYStart = (int)loc.getY()-this.aSchematic[0].length;
        for(int x=0; x<this.aSchematic.length; x++){
            for(int y=0; y<this.aSchematic[x].length; y++){
                Material mat = this.aSchematic[x][y];
                if(mat != Material.AIR){
                    Block bloc = world.getBlockAt(pXStart+y, pYStart+x);
                    if(bloc != null)
                        bloc.setBlock(mat);
                }
            }
        }
    }
    
}
