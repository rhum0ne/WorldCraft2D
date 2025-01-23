package engine;

import java.net.URL;
import javax.swing.ImageIcon;
import java.util.HashMap;
import engine.Game;
/**
 * Enumeration Material - écrire ici la description de l'énumération
 *
 * @author (votre nom)
 * @version (numéro de version ou date)
 */
public enum Material
{
    GRASS(Type.DIRT, "GRASS", "grass.png", "Grass Block", new MaterialData(true, true, false)),
    DIRT(Type.DIRT, "DIRT", "dirt.png", "Dirt", new MaterialData(true, true, true)),
    
    STONE(Type.ROCK, "STONE", "stone.png","Stone", new MaterialData(true, true, true)),
    COBBLESTONE(Type.ROCK, "COBBLESTONE", "cobble.png", "Cobblestone", new MaterialData(true, true, true)),
    STONE_BRICKS(Type.ROCK, "STONE_BRICKS", "cobblestone_7.png", "Stone Bricks", new MaterialData(true,true,true)),
    CHISELED_STONE(Type.ROCK, "CHISELED_STONE", "cobblestone_5.png", "Chiseled Stone Bricks", new MaterialData(true,true,true)),
    
    CALCITE(Type.ROCK, "CALCITE", "calcite.png", "Calcite", new MaterialData(true,true,true)),
    CALCITE_BRICKS(Type.ROCK, "CALCITE_BRICKS", "calcite_4.png", "Calcite Bricks", new MaterialData(true,true,true)),
    CHISELED_CALCITE(Type.ROCK, "CHISELED_CALCITE", "calcite_3.png", "Chiseled Calcite", new MaterialData(true,true,true)),
    
    BASALT(Type.ROCK, "BASALT", "basalt.png", "Basalt", new MaterialData(true, true, true)),
    DARK_BRICKS(Type.ROCK, "DARK_BRICKS", "dark-bricks.png", "Bricks", new MaterialData(true, true, true)),
    
    ICE(Type.ROCK, "ICE", "ice.png", "Ice", new MaterialData(true, true,true, true, 0.99)),
    
    WOOD(Type.WOOD, "WOOD", "wood.png", "Wood", new MaterialData(true, true, true)),
    LEAVES(Type.PLANT, "LEAVES", "leaves.png", "Leaves", new LeavesMaterial()),
    PLANKS(Type.WOOD, "PLANKS", "planks.png", "Planks", new MaterialData(true, true, true)),
    SAPPLING(Type.PLANT, "SAPPLING", "sappling.png", "Sappling", new SapplingMaterial()),
    
    BLUE_FLOWER(Type.PLANT, "BLUE_FLOWER", "blue-flower.png", "Blue Flower", new MaterialData(true, true, true, false)),
    RED_FLOWER(Type.PLANT, "RED_FLOWER", "red-flower.png", "Red Flower", new MaterialData(true, true, true, false)),
    GRASS_PLANT(Type.PLANT, "GRASS_PLANT", "grass-plant.png", "Grass", new MaterialData(true, true, true, false)),
    
    DARK_PLANKS(Type.WOOD, "DARK_PLANKS", "dark_planks.png", "Dark Planks", new MaterialData(true, true, true)),
    AIR(Type.ROCK, "AIR", "nope", "Ez dev", new MaterialData(true, true, false, false, 0.95)),
    
    
    LANTERN(Type.DECORATION, "LANTERN", "lantern.png", "Lantern", new MaterialData(true, true, false)),
    ROPE(Type.DECORATION, "ROPE", "chain_2.png", "Rope", new MaterialData(true, true, false)),
    CHAIN(Type.DECORATION, "CHAIN", "chain_5.png", "Chain", new MaterialData(true, true, false)),
    TRAP(Type.WOOD, "TRAP", "trap.png", "Trap", new OpenedTrapMaterial()),
    CLOSED_TRAP(Type.WOOD, "CLOSED_TRAP", "closed_trap.png", "Closed Trap",new ClosedTrapMaterial()),
    
    
    AXE(Type.TOOL, "AXE", "axe.png", "Axe", new AxeData()),
    CHISEL(Type.TOOL, "CHISEL", "chisel.png", "Chisel", new MaterialData(false, false, false)),
    PICKAXE(Type.TOOL, "PICKAXE", "pickaxe.png", "Pickaxe", new PickaxeData()),
    KNIFE(Type.TOOL, "KNIFE", "knife.png", "Knife", new KnifeData()),
    
    
    COOKIE(Type.FOOD, "COOKIE", "cookie.png", "Cookie", new FoodMaterial(2, 100)),
    COKE(Type.FOOD, "COKE", "coke.png", "Coke", new FoodMaterial(3, 1000)),
    APPLE(Type.FOOD, "APPLE", "apple.png", "Apple", new FoodMaterial(10, 1000)),
    BACON(Type.FOOD, "BACON", "bacon.png", "Bacon", new FoodMaterial(6, 500)),
    NUT(Type.FOOD, "NUT", "nut.png", "Nut", new FoodMaterial(2, 100));
    
    private static HashMap<Material, ImageIcon> TEXTURES = new HashMap<>();
    private static HashMap<String, Material> MATERIALS_NAMES = new HashMap<>();
    private static HashMap<String, Material> PARSEMATERIAL = new HashMap<>();
    
    private Type aType;
    private String aIDName;
    private String aPath;
    private String aName;
    private MaterialData aData;
    Material(final Type pType, String pIDName, final String path, final String pName, final MaterialData data){
        this.aPath = path;
        this.aType = pType;
        this.aIDName = pIDName;
        this.aName = pName;
        this.aData = data;
    }
    
    public String toString(){ return this.aIDName; }
    
    public Type getType(){
        return this.aType;
    }
    
    public MaterialData getData(){
        return this.aData;
    }
    
    public static void load(){
        loadTextures();
        loadLootingTables();
    }
    
    private static void loadTextures(){
        for(Material mat : Material.values()){
            if(mat.equals(AIR)) continue;
            URL vImageURL = Game.getInstance().getClass().getClassLoader().getResource( "assets\\" + mat.getTexturePath() );
            if ( vImageURL == null )
                vImageURL = GameEngine.getInstance().getClass().getClassLoader().getResource( "assets\\placeholder.png" );
            TEXTURES.put(mat, new ImageIcon( vImageURL ));
            MATERIALS_NAMES.put(mat.getName().toLowerCase(), mat);
            PARSEMATERIAL.put(mat.aIDName, mat);
        }
    }
    
    private static void loadLootingTables(){
        Material.GRASS.getData().addLoot(Material.DIRT);
        Material.CLOSED_TRAP.getData().addLoot(Material.TRAP);
        Material.STONE.getData().addLoot(Material.COBBLESTONE);
    }
    
    public String getTexturePath(){ return this.aPath; }
    
    public String getName(){ return this.aName; }
    
    public ImageIcon getTexture(){
        return TEXTURES.get(this);
    }
    
    public static Material parseMaterial(final String name){
        return MATERIALS_NAMES.get(name.toLowerCase());
    }
    
    public static Material strictParseMaterial(final String name){
        return PARSEMATERIAL.get(name);
    }
    
    public Sounds getSound(){ return this.aType.getSound(); }
}
