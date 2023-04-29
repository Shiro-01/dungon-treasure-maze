/**
 * this class is to store the item propertises found in the game and provide
 * them when needed.
 * it has five instance varibles the item weight , the description, attackpoints, healthPoints and defense points.
 * @author (AbdelRahman Hewal -ShIrO-)
 * @version (V1)
 */
public class Item extends Identity
{
    // instance variables
    private int weight;
    private String description;


    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight, int attackPoints, int defensePoints, int healthPoints)
    {
        // initialise instance variables
        super.name = name;
        super.attackPoints = attackPoints;
        super.defensePoints = defensePoints;
        super.healthPoints = healthPoints;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Method getWeight
     * --Weight getter--
     * @return The return weight value
     */
    public int getWeight()
    {
        // returnin weight
        return this.weight;
    }
    
    /**
     * Method getDescription
     * --Description getter--
     * @return The return the description value
     */
    public String getDescription()
    {
        // returning description
        return this.description;
    }
    
    public boolean isItem()
    {
        return true;
    }
}
