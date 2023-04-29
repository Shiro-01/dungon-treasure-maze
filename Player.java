import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Identity
{
    // instance variables - replace the example below with your own
    private int carriedWeight;
    private int lives;
    private Room currentLocation;
    private static final int CarryWeightLimit = 30;
    private ArrayList<Item> inventory;
    private Room previousLocation;    

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        // initialise instance variables
        super.name = name;
        super.healthPoints = 100;
        super.attackPoints = 5;
        super.defensePoints = 5;
        
        this.carriedWeight = 0;
        this.inventory = new ArrayList<Item>();
        this.previousLocation = null;
        this.lives = 3;
        
    }
    
    /*<-----------------Player methods called by game class-------------------------------------------------------------->*/
    /**
     * Method fightMonester
     * 
     * fight the monester in the current room
     *
     * @return The return value is true if u win the fight and update ur HP, return false 
     * when u die and updates the player lifes count and reset u to the previous room.
     */
    public boolean fightMonester()
    {
        // the moster damage to the player HP after subtracting the defense points
        int damagePoints = currentLocation.getMonester().getAttackPoints() - this.defensePoints;
        
        // if the damage points is less than or equals zero, then u have already wons,
        if(damagePoints <= 0)
        {
            //removing the monester from the rooom
            currentLocation.removeMonester();
            return true;
        }
        
        // repeating the damage FOR EACH OF THEM THE PLAYER AND THE  MONSTER till one of them has ZERO HP
        while (this.healthPoints > 0 && currentLocation.getMonester().getHealthPoints() > 0)
        {
            this.healthPoints -= damagePoints;
            currentLocation.getMonester().setHealthPoints(currentLocation.getMonester().getHealthPoints() - (this.attackPoints - currentLocation.getMonester().getDefensePoints()));                 
        }
        
        // checking if the plaver wins return true ** as his HP > 0, so he wasn`t the reason to break the loop
        if(this.healthPoints > 0)
        {
            //removing the monester from the rooom
            currentLocation.removeMonester();
            return true;
        }
        // as not return yet means that the player was the one who lost his HP, so he lose. Updating the life counter
        lives -= 1;
        this.healthPoints = 100;
        this.currentLocation = this.previousLocation;
        return false;
    }
    

    /**
     * Method putItem
     * gives back an item from player items list to the room's list
     * @param item the item to be left
     * @return return true if it's left in the room successfully, and false if not, due to the room max limit
     */
    public boolean putItem(Item item)
    {
        //adding the item to the room and checking its condition
        if(this.currentLocation.addItem(item))
        {
            //removing it from the inventory and updating the status values.
            this.inventory.remove(item);
            this.carriedWeight -= item.getWeight();
            this.attackPoints -= item.getAttackPoints();
            this.defensePoints -= item.getDefensePoints();
            return true;
        }
        return false;
    }

    
    /**
     * Method takeItem
     *-- adds specific item from the room to the player inventory
     * @param item the item to be taken
     * @return The return value is true if it doesn't exceed the weight limit, and false if it exceeded it
     */
    public boolean takeItem(Item item)
    {
        if(this.carriedWeight + item.getWeight() <= CarryWeightLimit)
        {
            //removing the item from the room and checking its condition
            if(this.currentLocation.removeItem(item))
            {
            //adding it to the inventory and updating the status values.
                this.inventory.add(item);
                this.attackPoints += item.getAttackPoints();
                this.defensePoints += item.getDefensePoints();
                this.carriedWeight += item.getWeight();
                return true;
            }
        }
        return false;
    }

    /**
     * Method consumeItem
     *-- consumes the item that increases the health points
     * @param item A parameter represent the item to be consumed
     * @return The return true if it is consumable and false if not
     */
    public boolean consumeItem(Item item)
    {
        if (item.getHealthPoints() == 0)
        {
        return false;
        }
        
        if (this.inventory.remove(item))
        {
            this.carriedWeight -= item.getWeight();
            this.healthPoints += item.getHealthPoints();
            return true;
        }
        return false;
    }
    
    // /**
     // * Method printInventory
     // * --Will be called by game class
     // *
     // * prints the itemslist to the user with its description
     // */
    // public void printInventory()
    // {

        // System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        // System.out.printf("| %-10s | %-50s | %-6s | %-13s | %-14s | %-13s |%n", "Name", "Description", "Weight", "Attack Points", "Defense Points", "Health Points");
        // System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        
        // for (Item item : inventory)
        // {
            // System.out.printf("| %-10s | %-50s | %-6d | %-13d | %-15d| %-13d |%n", item.getName(), item.getDescription(), item.getWeight(), item.getAttackPoints(), item.getDefensePoints(), item.getHealthPoints());

        // }
        // System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        // System.out.printf("| %40s = %d/30                                                                           |%n","carriedWeight", carriedWeight);
        // System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
    // }
    
    /*<-----------------variables  getters-------------------------------------------------------------->*/
    public Room getCurrentLocation()
    {
        return this.currentLocation;
    }

    public ArrayList<Item> getInventory()
    {
        return this.inventory;
    }
    
    public int getLives()
    {
        return this.lives;
    }
    
    public int getCarriedWeight()
    {
        return this.carriedWeight;
    }
    
    public Room getPreviousLocation()
    {
        return this.currentLocation;
    }
    
    public boolean isPlayer()
    {
        return true;
    }
    /*<-----------------variables  setters-------------------------------------------------------------->*/
    public void setCurrentLocation(Room room)
    {
        this.currentLocation = room;
    }
    
    public void setPreviousLocation(Room room)
    {
        this.previousLocation = room;
    }
}
