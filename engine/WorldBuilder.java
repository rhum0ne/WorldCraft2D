package engine;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


/**
 * Décrivez votre classe WorldBuilder ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WorldBuilder
{
    private static Material[] plants = new Material[]{Material.GRASS_PLANT, Material.RED_FLOWER, Material.BLUE_FLOWER};
    
    private World aWorld;
    private GameEngine aEngine;
    
    public WorldBuilder(final World world, final GameEngine engine){
        this.aWorld = world;
        this.aEngine = engine;
        
        this.aEngine.getGUI().getGamePanel().createBlocks(-48);
        this.aEngine.getGUI().getGamePanel().createBlocks(96);
        
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            this.aWorld.getBlocks().put(bloc.getLocations(), bloc);
        }
    }
    
    public void load(File save){
        try { // pour "essayer" les instructions suivantes :
            Scanner vSc = new Scanner( save );
            while ( vSc.hasNextLine() ) {
                String vLigne = vSc.nextLine();
                
                String[] s = vLigne.split(World.separator);
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                Material mat = Material.strictParseMaterial(s[2]);
                
                this.aWorld.getBlockAt(x, y).setBlock(mat);
            } // while
        } catch ( final FileNotFoundException pFNFE ) {
            System.out.println("Error: file " + save.getName() + " not found.");
            this.create();
        }
    }
    
    public void create(){
        /*for(Block bloc : this.aEngine.getGUI().getBlocks()){
            this.aWorld.getBlocks().put(bloc.getLocations(), bloc);
            if(bloc.getLocations().getY() == 10) bloc.setBlock(Material.GRASS);
            else if(bloc.getLocations().getY() >= 10 && bloc.getLocations().getY() < 13) bloc.setBlock(Material.DIRT);
            else if(bloc.getLocations().getY() >= 13) bloc.setBlock(Material.STONE);
        }*/
          
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            if(bloc.getLocations().getY() >= 15) bloc.setBlock(Material.STONE);
        }
        
        createRelief();
        createRelief();
        createRelief();
        createRelief();
        
        
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            if(bloc.getType() != Material.STONE) continue;
            int x = (int)bloc.getLocations().getX();
            int y = (int)bloc.getLocations().getY();
            
            Block b3 = this.aWorld.getBlockAt(x, y-3);
            Block b2 = this.aWorld.getBlockAt(x, y-2);
            Block b1 = this.aWorld.getBlockAt(x, y-1);
            
            if(b3 == null || b3.getType() == Material.AIR ||
                b2 == null || b3.getType() == Material.AIR ||
                b1 == null || b3.getType() == Material.AIR){
                bloc.setBlock(Material.DIRT);
            }
        }
        
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            if(bloc.getType() != Material.DIRT) continue;
            int x = (int)bloc.getLocations().getX();
            int y = (int)bloc.getLocations().getY();
            Block b = this.aWorld.getBlockAt(x, y-1);
            
            if(b == null ||b.getType() == Material.AIR)
                bloc.setBlock(Material.GRASS);
        }
        
        generateVeins();
        
        addVegetation();
    }
    
    private void createRelief(){
        Seed seed = this.aWorld.getSeed();
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            if(bloc.getType() != Material.STONE) {continue; }
            int x = (int)bloc.getLocations().getX();
            int y = (int)bloc.getLocations().getY();
            
            if(this.aWorld.getBlockAt(x, y-1).getType() == Material.AIR){
                int i = 3;
                
                if(this.aWorld.getBlockAt(x-1, y-1) != null)
                    if(this.aWorld.getBlockAt(x-1, y-1).getType() == Material.STONE) i--;
                if(this.aWorld.getBlockAt(x+1, y-1) != null)
                    if(this.aWorld.getBlockAt(x+1, y-1).getType() == Material.STONE) i--;
                    
                //int a = x + y + x%3 + (10-x*y);
                int a = seed.get(Math.abs(Math.max((x*y)%9, (x+y)%9))); 
                int c = seed.get(Math.abs(a)%10)*y + x + i + y/2;
                    
                if((seed.get(seed.get(Math.abs(c*a+i)%10))*a + c)%(i)==0){
                    this.aWorld.getBlockAt(x, y-1).setBlock(Material.STONE); 
                }
            }
            
            else if(this.aWorld.getBlockAt(x, y-1).getType() == Material.STONE){
                int i = 4;
                
                if(this.aWorld.getBlockAt(x-1, y-1) != null)
                    if(this.aWorld.getBlockAt(x-1, y-1).getType() == Material.AIR) i--;
                if(this.aWorld.getBlockAt(x+1, y-1) != null)
                    if(this.aWorld.getBlockAt(x+1, y-1).getType() == Material.AIR) i--;
                if(this.aWorld.getBlockAt(x, y-1) != null)
                    if(this.aWorld.getBlockAt(x, y-1).getType() == Material.AIR) i--;;
                if(this.aWorld.getBlockAt(x-1, y-2) != null)
                    if(this.aWorld.getBlockAt(x-1, y-2).getType() == Material.AIR) i--;;
                if(this.aWorld.getBlockAt(x+1, y-1) != null)
                    if(this.aWorld.getBlockAt(x+1, y-2).getType() == Material.AIR) i--;
                    
                if(i>1) continue;
                if(i<1) i=1;
                int a = (int)Math.exp(y);
                int c = x*y*seed.get(a%10);
                    
                if((seed.get(seed.get(Math.abs(c/a))%10))%2==0){
                    this.aWorld.getBlockAt(x, y-1).setBlock(Material.STONE); 
                }
            }
        }
    }
    
    private void generateVeins(){
        Seed seed = this.aWorld.getSeed();
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            if(bloc.getType() != Material.STONE) continue;
            
            int x = (int)bloc.getLocations().getX();
            int y = (int)bloc.getLocations().getY();
            
            for(OreVein vein : OreVein.values()){
                if((((10-seed.get(0))*x+vein.getMin()*y+seed.get(8)*x*y+ vein.getMax()*(12-seed.get(9))*vein.getSpawnRate()))%(101-vein.getSpawnRate()) == 0){
                    this.spawnVein(vein, bloc.getLocations());
                    continue;
                }
            }
        }
    }
    
    private void spawnVein(OreVein vein, Location location){
        Seed seed = this.aWorld.getSeed();
        Location loc = location;
        for(int i=0; i<=vein.getMax(); i++){
            int dir = (int)Math.abs(loc.getX()*seed.get(3)+(10-seed.get(9))*i*loc.getY()+(11-seed.get(1))*loc.getDistanceFrom(location) + loc.getX()*loc.getY()*loc.getDistanceFrom(location))%4;
            
            int x = (int)loc.getX()+ ((dir)%2 * (dir -2));
            int y = (int)loc.getY()+ ((dir+1)%2* (dir -1));
            
            Block bloc = this.aWorld.getBlockAt(x, y);
            if(bloc== null) return;
            if(bloc.getType() != Material.STONE) continue;
            bloc.setBlock(vein.getType());
            
            loc = bloc.getLocations();
            
            if(i> vein.getMin() && i+x+y%9==0) break;
            
        }
        
    }
    
    private void addVegetation(){
        Seed seed = this.aWorld.getSeed();
        for(Block bloc : this.aEngine.getGUI().getBlocks()){
            if(bloc.getType() != Material.GRASS) continue;
            int x = (int)bloc.getLocations().getX();
            int y = (int)bloc.getLocations().getY();
            
            if((seed.get(0)*x + (7-seed.get(7))*y + x*y + y*y - seed.get(9)*x)%(Math.abs(x)%15+1) == 0) 
                spawnTree(new Location(x, y-1));
            else if((seed.get(0)*x + (7-seed.get(7))*y + x*y+seed.get(3))%5 == 0)  { 
                Block bloc2 = this.aWorld.getBlockAt(x, y-1);
                spawnPlant(bloc2);
            }
        }
    }
    
    private void spawnPlant(Block bloc){
        Material plant = plants[Math.abs((int)bloc.getLocations().getX())%plants.length];
        bloc.setBlock(plant);
    }
    
    public void spawnTree(Location loc){
        this.aWorld.spawn(Structures.TREE, loc);
    }
}
