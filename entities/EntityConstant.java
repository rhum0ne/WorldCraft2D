package entities;

import gui.Bar;
import java.util.HashMap;

/**
 * The EntityConstant class represents a numeric attribute of a living entity, such as health or aggressiveness.
 * It provides methods to manipulate the attribute's value within defined bounds and updates the game interface accordingly.
 * 
 * @version 1.0
 */
public class EntityConstant
{
    private String aName;
    private LivingEntity aEntity;
    private int aCurrent;
    private int aMax;
    private int aMin;
    private boolean aKill;
    
    /**
     * Constructs an EntityConstant object with specified initial values.
     * 
     * @param pName the name of the attribute
     * @param pCurrent the initial value of the attribute
     * @param pMin the minimum allowable value
     * @param pMax the maximum allowable value
     * @param pEntity the living entity this attribute belongs to
     * @param pKill whether the entity should be killed if the attribute reaches the minimum value
     */
    public EntityConstant(final String pName, final int pCurrent, final int pMin, final int pMax, final LivingEntity pEntity, final boolean pKill){
        this.aCurrent = pCurrent;
        this.aMin = pMin;
        this.aMax = pMax;
        this.aName = pName;
        this.aEntity = pEntity;
        this.aKill = pKill;
    }
    
    /**
     * Constructs an EntityConstant object with specified initial values and no kill condition.
     * 
     * @param pName the name of the attribute
     * @param pCurrent the initial value of the attribute
     * @param pMin the minimum allowable value
     * @param pMax the maximum allowable value
     * @param pEntity the living entity this attribute belongs to
     */
    public EntityConstant(final String pName, final int pCurrent, final int pMin, final int pMax, final LivingEntity pEntity){
        this(pName, pCurrent, pMin, pMax, pEntity, false);
    }
    
    /**
     * Gets the name of the attribute.
     * 
     * @return the name of the attribute
     */
    public String getName(){ return this.aName; }
    
    /**
     * Gets the current value of the attribute.
     * 
     * @return the current value of the attribute
     */
    public int get(){ return this.aCurrent; }
    
    /**
     * Gets the maximum allowable value of the attribute.
     * 
     * @return the maximum allowable value
     */
    public int getMax(){ return this.aMax; }
    
    /**
     * Gets the minimum allowable value of the attribute.
     * 
     * @return the minimum allowable value
     */
    public int getMin(){ return this.aMin; }
    
    public boolean isMin(){ return this.aCurrent == this.aMin; }
    public boolean isMax(){ return this.aCurrent == this.aMax; }
    
    public void setMax(){ this.aCurrent = this.aMax; }
    
    /**
     * Adds a specified value to the attribute, ensuring it does not exceed the maximum.
     * 
     * @param pAdd the value to add
     */
    public void add(final int pAdd){ 
        if(this.aCurrent + pAdd >= this.aMax) {
            this.aCurrent = this.aMax;
        } else {
            this.aCurrent += pAdd; 
        }
        this.updateInterface();
    }
    
    /**
     * Removes a specified value from the attribute, ensuring it does not go below the minimum.
     * 
     * @param pRem the value to remove
     */
    public void remove(final int pRem){ 
        if(this.aCurrent - pRem <= this.aMin) {
            this.aCurrent = this.aMin;
        } else {
            this.aCurrent -= pRem; 
        }
        this.updateInterface();
    }
    
    /**
     * Sets the attribute to a specified value.
     * 
     * @param pSet the value to set
     */
    public void set(final int pSet){ 
        this.aCurrent = pSet;
        this.updateInterface(); 
    }
    
    /**
     * Sets the maximum allowable value of the attribute.
     * 
     * @param pSet the maximum value to set
     */
    public void setMax(final int pSet){ 
        this.aMax = pSet;
        this.updateInterface(); 
    }
    
    /**
     * Sets the minimum allowable value of the attribute.
     * 
     * @param pSet the minimum value to set
     */
    public void setMin(final int pSet){ 
        this.aMin = pSet;
        this.updateInterface(); 
    }
    
    /**
     * Updates the game interface to reflect changes in the attribute's value.
     * If the attribute has a kill condition and its value reaches the minimum, the entity is killed.
     * If the entity is a player, the corresponding UI bar is updated.
     */
    public void updateInterface(){
        if(this.aKill && this.aCurrent == this.aMin){
            this.aEntity.kill();
        }
        if(!(aEntity instanceof Player)) return;
        HashMap<EntityConstant, Bar> vBars = aEntity.getEngine().getGUI().getBars();
        if(vBars.containsKey(this)){
            vBars.get(this).update();
        }
    }
}
