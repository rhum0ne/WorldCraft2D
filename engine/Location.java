package engine;

/**
 * Décrivez votre classe Location ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Location
{
    private double x;
    private double y;
    
    //Format coordonnées : 1.5 = 1 bloc et demi
    //Format pixels : 1 bloc et demi = 32 + 16 = 48
    
    public Location(double pX, double pY){
        this.x = pX;
        this.y = pY;
    }
    
    public Location(){ this(0, 0); }
    
    public Location(Location loc){
        this(loc.x, loc.y);
    }

    public void set(Location loc){
        this.x = loc.x;
        this.y = loc.y;
    }
    
    public double getX(){
        return this.x;
    }
    
    public int getXPixel(){
        return (int) (this.x*32);
    }
    
    public double getY(){
        return this.y;
    }
    
    public int getYPixel(){
        return (int) (this.y*32);
    }
    
    public void setY(double pY){
        this.y = pY;
    }
    
    public void setYPixel(double pY){
        this.y = pY/32.0;
    }
    
    public void setX(double pX){
        this.x = pX;
    }
    
    public void addY(final double y){
        this.y += y;
    }
    
    public void addX(final double x){
        this.x += x;
    }
    
    public void setXPixel(double pX){
        this.x = pX/32.0;
    }
    
    public void setCoordinates(double pX, double pY){
        this.x = pX;
        this.y = pY;
    }
    
    public void addCoordinates(double pX, double pY){
        this.x += pX;
        this.y += pY;
    }
    
    public double getDistanceFrom(final Location pLoc){
        return Math.sqrt((this.x-pLoc.x)*(this.x-pLoc.x) + (this.y-pLoc.y)*(this.y-pLoc.y));
    }
}
