package engine;


/**
 * Enumeration Structures - écrire ici la description de l'énumération
 *
 * @author (votre nom)
 * @version (numéro de version ou date)
 */
public enum Structures
{
    TREE(new Structure());
    
    Structure structure;
    Structures(final Structure pS){
        this.structure = pS;
    }
    
    public Structure getStructure(){ return this.structure; }
    
    
    public static void createStructures(){
        createTree();
    }
    
    private static void createTree(){
        Structure tree = TREE.structure;
        tree.setMaterial('A', Material.AIR);
        tree.setMaterial('W', Material.WOOD);
        tree.setMaterial('L', Material.LEAVES);
        tree.setSchem(new char[][]
        {   {'A','L','L','L','A'},
            {'L','L','L','L','L'},
            {'L','L','W','L','L'},
            {'A','A','W','A','A'},
            {'A','A','W','A','A'},
            {'A','A','W','A','A'} });
        tree.validate();
    }
}
