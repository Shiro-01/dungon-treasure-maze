import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.io.Serializable;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @Edits by (AbdelRahman Hewal -ShIrO-)
 * @version v2
 */
public class Room implements Serializable
{
    String description;
    private HashMap<String, Room> directions;
    private ArrayList<Item> items;
    private static final int ItemsLimit = 5;
    private int itemsCount;
    private Monester monester;
    private Item secretKey;
    private String imagPath;
    private String name;
    // private BufferedImage img;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description, String imagPath) 
    {
        this.directions = new HashMap<String, Room>();
        this.description = description;
        this.items = new ArrayList<Item>();
        this.itemsCount = 0;
        monester = null;
        secretKey = null;
        this.imagPath = imagPath;
        this.name = name;
        // img = ImageIO.read(new FileInputStream(path));
    }

    /*<-----------------Room and Exits related methods-------------------------------------------------------------->*/
    /**
     * Method setExit
     *
     * @param direction A parameter which is the key of the hashmap
     * that describes the location of another room relative to this one.
     * @param room A parameter the room in that direction.
     */
    public void setExit(String direction, Room room)
    {
        // making sure that there is no duplicates to avoid over write of data
        if(directions.containsKey(direction))
        {
            directions.remove("direction");
            directions.put(direction,room);
        }
        else
        {
            directions.put(direction,room);
        }
    }

    /**
     * Method getExit
     *
     * @param direction A parameterwhich is the key of the HashMap, must not be a null
     * @return The return value is the coresponding room to vthat direction
     */
    public Room getExit(String direction) //thows exception -- u will use it if u r offesnive,/ note: the exception is the super class of every exception-- this is a bad practice we should not use it // this was an offensive programming style and we r turning it to defensive where     // offensive means that u as a service u dont care about the eception errors like null exception, so u just use the throw statment the u don't care about the excpetion to make the compiler neglect that // defensive mesns that u as a serive are the one responsible to filter the arguments and handel the exception and u are supposed to use try and catch to handel that exception
    {
        if(direction == null) {
         throw new IllegalArgumentException("the direction is a null");      //io exception is about the directory that u maybe dont have access to it,  //file not found exception mesns the problem is about the path that the file may not be there
         //run  time error can not be catched must be handeled manually -- u always need to determine which style u r gonna use either offensive or deffensive, before u start to write code, so, u can determine whos responability to handel the exception either the client or the service
        
            //referntial integrity: binarry IO, when i write an object like the room into an file                           , must extends resizable. not only in the main class but for all the class the are used in that class  
        }
        
        return directions.get(direction);
    }

    /**
     * @return The Full description of the room witb its currents Exits.
     */
    public String getDescription()
    {
        return description;

    }

    /**
     * Method getDirections
     *
     * @return The return value is the possible directoin to go
     */
    public Set<String> getDirections()
    {
        return directions.keySet();
    }

    /**
     * Method setSecretKey
     *--secrert key setter -- 
     * @param item A parameter that will need to oopen the next room
     */
    public void setSecretKey(String name, String description, int weight, int attackPoints, int defensePoints, int healthPoints)

    {
        secretKey = new Item(name, description, weight, attackPoints, defensePoints, healthPoints);

    }
    
    /**---ask-----------------**
     * Method holdsKey
     * checks special condition to open anew room in the environment
     *
     * @return The return value is true if the key item included in the room
     */
    public boolean holdsKey()
    {
        if (secretKey == null)
        {
            return false;
        }
        
        for(Item item : items)
        {
            if(item.getName().equals(secretKey.getName()))
            {
                return true;
            }
        }
        return false;
    }
        
    /*<-----------------Items Arraylist related methods-------------------------------------------------------------->*/
    /**
     * Method setItem
     *-- items arraylist Setter -- adds an item by just declaring ints propetises
     *-- called by the environment class --
     *
     * @param newItems the arrsylist of items to enter.
     * @return  return true if the addation is done, otherwise return false and dont add any thing
     * if it will exceed the itemlimit.
     */
    public boolean setItem(String name, String description, int weight, int attackPoints, int defensePoints, int healthPoints)
    {
        // adding the new items as an array list to the main one
        if(itemsCount + 1 <= ItemsLimit)
        {
            items.add(new Item(name, description, weight, attackPoints, defensePoints, healthPoints));
            itemsCount++;
            return true; // return true if the addation is done
        }
        else
        {
            return false;   // return flase if the addation fails and its valueis the current capacity
            //return itemsCount doesnt work;
        }

    }

    /**
     * Method removeItem
     *-- will be used by the player class --
     * @param item the item to be removed.
     * @return returns true if succesfully removed or false if not.
     */
    public boolean removeItem(Item item)
    {
        // updating the items count if sucessfull -- consistancy
        if(items.remove(item))
        {
            itemsCount--;
            return true; //true if succesfully removed
        }
        else
        {
            return false;  //false if failed
        }
    }

    /**
     * Method addItem
     *-- will be called by the player class -- adds specific item to the room items regarfing the maximun condition
     * @param item the item to be add.
     * @return returns true if succesfully added or false if not
     */
    public boolean addItem(Item item)
    {
        if(itemsCount + 1 < ItemsLimit)
        {
            items.add(item);
            // updating the items count if sucessfull -- consistancy
            itemsCount++;
            return true; //true if succesfully removed
        }
        else{
            return false; //false if failed
        }
    }

    /**
     * Method printItemList
     * --Will be called by game class
     *
     * prints the itemslist to the user with its description
     */
    public void printItemList()
    {

        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        System.out.printf("| %-10s | %-50s | %-6s | %-13s | %-13s | %-13s |%n", "Name", "Description", "Weight", "Attack Points", "Defense Points", "Health Points");
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");

        for (Item item : items)
        {
            System.out.printf("| %-10s | %-50s | %-6d | %-13d | %-14d | %-13d |%n", item.getName(), item.getDescription(), item.getWeight(), item.getAttackPoints(), item.getDefensePoints(), item.getHealthPoints());

        }
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
        System.out.printf("| %44s = %d                                                                          |%n","itemsCouts", itemsCount);
        System.out.printf("+---------------------------------------------------------------------------------------------------------------------------+%n");
    }
    
    /**
    *items arraylist getter
    *
    * @return   items arraylist
    */
    public ArrayList<Item> getItems()
    {
        // returns the items arraylist
        return items;
    }
    
    //<-----------------Monester related methods-------------------------------------------------------------->
    /**
     * Method getMonester
     *-- Monester getter --
     * @return Monester
     */
    public Monester getMonester()
    {
        return monester;
    }    

    /**
     * Method setMonester
     * sets a monester by his initial parameters
     * @param name the monester name
     * @param healthPoints monester health points
     * @param attackPoints monester attackpoints
     */
    public void setMonester(String name, int healthPoints, int attackPoints, int defensePoints)
    {
        monester = new Monester(name, healthPoints, attackPoints, defensePoints);
    }    

    /**
     * Method removeMonester
     * Just removes the monester in this room
     */
    public void removeMonester()
    {
        monester = null;
    }

    /**
     * Method printMonester
     * prints monester details
     */
    public void printMonester()
    {
        if(monester != null)
        {
            System.out.printf("%s:  HealthPoints: %d, AttackPoints: %d %n", this.monester.getName(), this.monester.getHealthPoints(), this.monester.getAttackPoints());
        }
        else
        {
            System.out.println("there is no monester in this room");
        }
        
    }
    
    //Immage Getter 
    public String getImagePath()
    {
        return imagPath;
    }
    
    //Room name getter
    public String getRoomName()
    {
        return this.name;
    }
}
