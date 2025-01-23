package engine;
import java.util.Random;


/**
 * Décrivez votre classe Seed ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Seed
{
    private int[] seed;
    
    public static Seed random(){
        Random rdm = new Random();
        int[] a = new int[10];
        for(int i=0; i<10; i++){
            a[i] = rdm.nextInt(10);
        }
        return new Seed(a);
    }
    
    public static Seed create(String enteredSeed){
        int[] seed = new int[enteredSeed.length()];
        for(int i=0; i<seed.length; i++){
            char c = enteredSeed.charAt(i);
            if(Character.isDigit(c)){
                seed[i] = c - '0';
            }
            else seed[i] = c%10;
        }
        return Seed.create(seed);
    }
    
    public static Seed create(int[] a){
        if(a.length < 10){
            int[] seed = new int[10];
            for(int i=0; i<10; i++){
                if(i<a.length) {
                    seed[i] = a[i];
                }
                else { 
                    seed[i] = 0;
                }
            }
            return new Seed(seed);
        }
        return new Seed(a);
    }
    
    /**
     * Constructeur d'objets de classe Seed
     */
    public Seed(int[] seed)
    {
        this.seed = seed;
    }
    
    public int[] get(){ return this.seed;}
    public int get(int i){ return this.seed[i]; }
    
    public String toString(){ 
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<seed.length; i++){
            sb.append(seed[i]);
        }
        return sb.toString();
    }
}
