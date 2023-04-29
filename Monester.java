/**
 * Write a description of class Monester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Monester extends Identity
{
    // instance variables 
    private int fullHealth;
    /**
     * Constructor for objects of class Monester
     */
    public Monester(String name, int healthPoints, int attackPoints, int defensePoints)
    {
        // initialise instance variables
        super.name = name;
        super.healthPoints = healthPoints;
        super.attackPoints = attackPoints;
        super.defensePoints = defensePoints;
        this.fullHealth = healthPoints;
    }
    
    /*<-----------------variables  setters-------------------------------------------------------------->*/
    /**
     * Method setHealthPoints
     *
     * @param healthPoints
     */
    public void setHealthPoints(int healthPoints)
    {
        // put your code here
        super.healthPoints = healthPoints;
    }
    
    public int getFullHealth()
    {
        return fullHealth;
    }
    
    public boolean isMonester()
    {
        return true;
    }
}
