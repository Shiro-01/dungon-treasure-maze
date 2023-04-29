import java.io.Serializable;

/**
 * Write a description of class Identity here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Identity implements Serializable
{
    // instance variables - replace the example below with your own
    protected String name;
    protected int healthPoints;
    protected int attackPoints;
    protected int defensePoints;
    
    /**
     * Constructor for objects of class Identity
     */
    public Identity()
    {
        // initialise instance variables
    }

    
    
    /**
     * Method getAttackPoints
     * --AttackPoints getter--
     * @return The return the AttackPoints value.
     */
    public int getAttackPoints()
    {
        // returning description
        return this.attackPoints;
    }
    
    /**
     * Method getDefensePoints
     * --DefensePoints getter--
     * @return The return the DefensePoints value.
     */
    public int getDefensePoints()
    {
        // returning description
        return this.defensePoints;
    }
    
    /**
     * Method getHealthPoints
     * --AttackPoints getter--
     * @return The return the AttackPoints value.
     */
    public int getHealthPoints()
    {
        // returning description
        return this.healthPoints;
    }
    
    public String getName()
    {
        // returning description
        return this.name;
    }
    
    public boolean isItem()
    {
        return false;
    }
    
    public boolean isPlayer()
    {
        return false;
    }
    
    public boolean isMonester()
    {
        return false;
    }
}
